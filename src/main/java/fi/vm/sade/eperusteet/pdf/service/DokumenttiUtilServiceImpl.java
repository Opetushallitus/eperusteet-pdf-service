package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.service.external.CommonExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class DokumenttiUtilServiceImpl implements DokumenttiUtilService {

    private static final float COMPRESSION_LEVEL = 0.9f;

    @Autowired
    private CommonExternalService commonExternalService;

    @Override
    public void buildImages(DokumenttiBase docBase, Long sisaltoId, DokumenttiTyyppi tyyppi) {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            XPathExpression expression = xpath.compile("//img");
            NodeList list = (NodeList) expression.evaluate(docBase.getDocument(), XPathConstants.NODESET);

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                String id = element.getAttribute("data-uid");
                String src = element.getAttribute("src");

                if (ObjectUtils.isEmpty(id)) {
                    continue;
                }

                UUID uuid = DokumenttiTyyppi.TOTEUTUSSUUNNITELMA.equals(tyyppi) ? ylopsUUIDHandling(id, src) : UUID.fromString(id);

                // Ladataan kuvan data muistiin
                InputStream in;
                try {
                    in = commonExternalService.getLiitetiedosto(sisaltoId, uuid, tyyppi);
                }
                catch (Exception e) {
                    log.error("Liitettä ei löytynyt, id={}, UUID={}", sisaltoId, uuid);
                    return;
                }

                // Tehdään muistissa olevasta datasta kuva
                BufferedImage bufferedImage = ImageIO.read(in);

                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();

                // Muutetaan kaikkien kuvien väriavaruus RGB:ksi jotta PDF/A validointi menee läpi
                // Asetetaan lisäksi läpinäkyvien kuvien taustaksi valkoinen väri
                BufferedImage tempImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                        BufferedImage.TYPE_3BYTE_BGR);
                tempImage.getGraphics().setColor(new Color(255, 255, 255, 0));
                tempImage.getGraphics().fillRect(0, 0, width, height);
                tempImage.getGraphics().drawImage(bufferedImage, 0, 0, null);
                bufferedImage = tempImage;

                ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
                ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
                jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                jpgWriteParam.setCompressionQuality(COMPRESSION_LEVEL);

                // Muunnetaan kuva base64 enkoodatuksi
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                MemoryCacheImageOutputStream imageStream = new MemoryCacheImageOutputStream(out);
                jpgWriter.setOutput(imageStream);
                IIOImage outputImage = new IIOImage(bufferedImage, null, null);
                jpgWriter.write(null, outputImage, jpgWriteParam);
                jpgWriter.dispose();
                String base64 = Base64.getEncoder().encodeToString(out.toByteArray());

                // Lisätään bas64 kuva img elementtiin
                element.setAttribute("width", String.valueOf(width));
                element.setAttribute("height", String.valueOf(height));
                element.setAttribute("src", "data:image/jpg;base64," + base64);
            }

        } catch (XPathExpressionException | IOException | NullPointerException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void buildKuva(DokumenttiBase docBase, Kuvatyyppi kuvatyyppi, DokumenttiTyyppi dokumenttiTyyppi, Long ktId) {
        Dokumentti dokumentti = docBase.getDokumentti();

        Element head = docBase.getHeadElement();
        Element element = docBase.getDocument().createElement(kuvatyyppi.toString());
        Element img = docBase.getDocument().createElement("img");

        byte[] kuva;
        try {
            kuva = commonExternalService.getDokumenttiKuva(dokumentti.getSisaltoId(), kuvatyyppi, dokumentti.getKieli(), dokumenttiTyyppi, ktId);
        } catch (Exception e) {
            log.warn("Kuvaa ei löytynyt, id={}, tyyppi={}", dokumentti.getSisaltoId(), kuvatyyppi);
            return;
        }

        String base64 = Base64.getEncoder().encodeToString(kuva);
        img.setAttribute("src", "data:image/jpg;base64," + base64);

        element.appendChild(img);
        head.appendChild(element);
    }

    @Override
    public TermiDto getTermiFromExternalService(Long id, String avain, DokumenttiTyyppi tyyppi) {
        try {
            return commonExternalService.getTermi(id, avain, tyyppi);
        } catch (Exception e) {
            log.info("Termiä ei löytynyt id:lle '{}', avain='{}'", id, avain);
        }
        return null;
    }

    private UUID ylopsUUIDHandling(String id, String src) {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            // Jos data-uuid puuttuu, koitetaan hakea src:n avulla
            if (src.contains("eperusteet-ylops-service")) {
                String[] parts = src.split("/");
                if (parts.length > 1 && Objects.equals(parts[parts.length - 2], "kuvat")) {
                    uuid = UUID.fromString(parts[parts.length - 1]);
                }
            }
        }
        if (uuid == null) {
            log.error("src {}, id {} ", src, id);
            throw new BusinessRuleViolationException("kuva-uuid-ei-loytynyt");
        }
        return uuid;
    }
}
