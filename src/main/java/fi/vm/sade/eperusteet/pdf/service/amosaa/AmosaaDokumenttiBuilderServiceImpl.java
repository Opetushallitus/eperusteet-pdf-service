package fi.vm.sade.eperusteet.pdf.service.amosaa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.Reference;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.SuorituspolkuRiviDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.ArvioinninKohdeDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.ArvioinninKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.ArviointiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoLaajaAlaisenOsaamisenAlueDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.OsaamisenTavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.OsaamistasonKriteeriDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.RakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.SuoritustapaLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TutkinnonOsaViiteSuppeaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TutkinnonosaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.ValmaTelmaSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimuksenKohdeDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimuksenKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimusDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoKielitaitotasoExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoLaajaAlainenOsaaminenExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoOpintoExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KoulutuksenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.OmaTutkinnonosaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.OpintokokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SuorituspolkuExportDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoMetadataDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.MuodostumisSaantoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.OsaamistasoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiAmosaa;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiRivi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiTaulukko;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.dto.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiUtilService;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import fi.vm.sade.eperusteet.pdf.service.external.AmosaaService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.service.external.KoodistoClient;
import fi.vm.sade.eperusteet.pdf.utils.CharapterNumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;

@Slf4j
@Service
public class AmosaaDokumenttiBuilderServiceImpl implements AmosaaDokumenttiBuilderService {

    private static final DecimalFormat df2 = new DecimalFormat("0.##");

    @Autowired
    private EperusteetService eperusteetService;

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private KoodistoClient koodistoClient;

    @Autowired
    private AmosaaService amosaaService;

    @Autowired
    private DokumenttiUtilService dokumenttiUtilService;

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Override
    public Document generateXML(OpetussuunnitelmaKaikkiDto ops, GeneratorData generatorData) throws ParserConfigurationException, JsonProcessingException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Luodaan XHTML pohja
        Element rootElement = doc.createElement("html");
        rootElement.setAttribute("lang", generatorData.getKieli().toString());
        doc.appendChild(rootElement);

        Element headElement = doc.createElement("head");

        // Poistetaan HEAD:in <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        if (headElement.hasChildNodes()) {
            headElement.removeChild(headElement.getFirstChild());
        }

        Element bodyElement = doc.createElement("body");

        rootElement.appendChild(headElement);
        rootElement.appendChild(bodyElement);

        // Apuluokka datan s??il??miseen generoinin ajaksi
        DokumenttiAmosaa docBase = new DokumenttiAmosaa();
        docBase.setDocument(doc);
        docBase.setHeadElement(headElement);
        docBase.setBodyElement(bodyElement);
        docBase.setGenerator(new CharapterNumberGenerator());
        docBase.setKieli(generatorData.getKieli());
        docBase.setGeneratorData(generatorData);
        docBase.setOpetussuunnitelma(ops);

        if (ops.getPeruste() != null) {
            PerusteKaikkiDto perusteKaikkiDto = amosaaService.getPerusteKaikkiDto(ops.getPeruste().getId());
            docBase.setPeruste(perusteKaikkiDto);
        }

        // Kansilehti & Infosivu
        addMetaPages(docBase);

        // Sis??lt??elementit
        addTekstit(docBase);

        // Alaviitteet
        buildFootnotes(docBase);

        // Kuvat
        dokumenttiUtilService.buildImages(docBase, generatorData);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.kansikuva, generatorData);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.ylatunniste, generatorData);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.alatunniste, generatorData);

        return doc;
    }

    private void addMetaPages(DokumenttiAmosaa docBase) {
        OpetussuunnitelmaDto ops = docBase.getOpetussuunnitelma();

        Element title = docBase.getDocument().createElement("title");
        String nimi = getTextString(docBase, ops.getNimi());
        title.appendChild(docBase.getDocument().createTextNode(nimi));
        docBase.getHeadElement().appendChild(title);

        // Kuvaus
        String kuvaus = getTextString(docBase, ops.getKuvaus());
        if (kuvaus != null && kuvaus.length() != 0) {
            Element description = docBase.getDocument().createElement("description");
            addTeksti(docBase, kuvaus, "div", description);
            docBase.getHeadElement().appendChild(description);
        }

        if (ops.getPeruste() != null) {
            String perusteNimi = getTextString(docBase, ops.getPeruste().getNimi());
            if (perusteNimi != null) {
                Element perusteNimiEl = docBase.getDocument().createElement("meta");
                perusteNimiEl.setAttribute("name", "perusteNimi");
                perusteNimiEl.setAttribute("content", perusteNimi);
                docBase.getHeadElement().appendChild(perusteNimiEl);
            }
        }

        // P????t??snumero
        String paatosnumero = ops.getPaatosnumero();
        if (paatosnumero != null) {
            Element paatosnumeroEl = docBase.getDocument().createElement("meta");
            paatosnumeroEl.setAttribute("name", "paatosnumero");
            paatosnumeroEl.setAttribute("content", paatosnumero);
            docBase.getHeadElement().appendChild(paatosnumeroEl);
        }

        // Hyv??ksyj??
        String hyvaksyja = ops.getHyvaksyja();
        if (hyvaksyja != null) {
            Element hyvaksyjaEl = docBase.getDocument().createElement("meta");
            hyvaksyjaEl.setAttribute("name", "hyvaksyja");
            hyvaksyjaEl.setAttribute("content", hyvaksyja);
            docBase.getHeadElement().appendChild(hyvaksyjaEl);
        }

        // P????t??sp??iv??m????r??
        Date paatospaivamaara = ops.getPaatospaivamaara();
        if (paatospaivamaara != null) {
            Element paatospaivamaaraEl = docBase.getDocument().createElement("meta");
            paatospaivamaaraEl.setAttribute("name", "paatospaivamaara");
            String paatospaivamaaraText = new SimpleDateFormat("d.M.yyyy").format(paatospaivamaara);
            paatospaivamaaraEl.setAttribute("content", paatospaivamaaraText);
            docBase.getHeadElement().appendChild(paatospaivamaaraEl);
        }

        // Voimaantulop??iv??m????r??
        Date voimaantulopaivamaara = ops.getVoimaantulo();
        if (voimaantulopaivamaara != null) {
            Element voimaantulopaivamaaraEl = docBase.getDocument().createElement("meta");
            voimaantulopaivamaaraEl.setAttribute("name", "voimaantulopaivamaara");
            String voimaantulopaivamaaraText = new SimpleDateFormat("d.M.yyyy").format(voimaantulopaivamaara);
            voimaantulopaivamaaraEl.setAttribute("content", voimaantulopaivamaaraText);
            docBase.getHeadElement().appendChild(voimaantulopaivamaaraEl);
        }

        Element pdfluotu = docBase.getDocument().createElement("meta");
        pdfluotu.setAttribute("name", "pdfluotu");
        pdfluotu.setAttribute("content", new SimpleDateFormat("d.M.yyyy").format(new Date()));
        pdfluotu.setAttribute("translate", messages.translate("docgen.pdf-luotu", docBase.getKieli()));
        docBase.getHeadElement().appendChild(pdfluotu);
    }

    private void addTekstit(DokumenttiAmosaa docBase) {
        // TODO: mist?? haetaan nyt??
//        SisaltoViite tekstit = tkvRepository.findOneRoot(docBase.getOpetussuunnitelma());
        SisaltoViiteExportDto tekstit = docBase.getOpetussuunnitelma().getSisalto();
        if (tekstit != null) {
            addSisaltoviite(docBase, tekstit);
        }
    }

    private void addSisaltoviite(DokumenttiAmosaa docBase, SisaltoViiteExportDto viite) {
//        for (SisaltoViiteExportDto lapsi : viite.getLapset()) {
//            if (lapsi == null) {
//                return;
//            }
//
//            if (lapsi.getTyyppi().equals(SisaltoTyyppi.SUORITUSPOLUT) && CollectionUtils.isEmpty(lapsi.getLapset())) {
//                continue;
//            }
//
//            TekstiKappaleJulkinenDto tekstiKappale = lapsi.getTekstiKappale();
//            StringBuilder otsikkoBuilder = new StringBuilder();
//            otsikkoBuilder.append(getTextString(docBase, lapsi.getNimi()));
//
//            if (lapsi.getTyyppi().equals(SisaltoTyyppi.TUTKINNONOSA) && lapsi.getTosa() != null) {
//                TutkinnonosaDto tosa = lapsi.getTosa();
//                switch (tosa.getTyyppi()) {
//                    case OMA:
//                        if (tosa.getOmatutkinnonosa() == null
//                                || tosa.getOmatutkinnonosa().getKoodi() == null) {
//                            break;
//                        }
//                        String koodi = tosa.getOmatutkinnonosa().getKoodi();
//
//                        otsikkoBuilder
//                                .append(" (")
//                                .append(koodi)
//                                .append(")");
//                        break;
//                    case PERUSTEESTA:
//                        Long perusteenTutkinnonosaId = tosa.getPerusteentutkinnonosa();
//                        PerusteKaikkiDto peruste = docBase.getPeruste();
//                        if (perusteenTutkinnonosaId == null || peruste == null) {
//                            break;
//                        }
//                        peruste.getTutkinnonOsat().stream()
//                                .filter(dto -> dto.getId().equals(perusteenTutkinnonosaId))
//                                .findAny()
//                                .ifPresent(dto -> otsikkoBuilder
//                                        .append(" (")
//                                        .append(dto.getKoodiArvo())
//                                        .append(")"));
//                        break;
//                    default:
//                        break;
//                }
//            }

//            if (lapsi.getTyyppi().equals(SisaltoTyyppi.OPINTOKOKONAISUUS)
//                    && lapsi.getOpintokokonaisuus() != null
//                    && lapsi.getOpintokokonaisuus().getLaajuus() != null && lapsi.getOpintokokonaisuus().getLaajuus().compareTo(BigDecimal.ZERO) > 0
//                    && lapsi.getOpintokokonaisuus().getLaajuusYksikko() != null) {
//                otsikkoBuilder
//                        .append(", ")
//                        .append(df2.format(lapsi.getOpintokokonaisuus().getLaajuus()))
//                        .append(" ")
//                        .append(messages.translate(selectLaajuusYksikkoMessage(lapsi.getOpintokokonaisuus().getLaajuusYksikko()), docBase.getKieli()));
//            }
//
//            if (lapsi.getTyyppi().equals(SisaltoTyyppi.KOULUTUKSENOSA)) {
//                otsikkoBuilder
//                        .append(", ")
//                        .append(lapsi.getKoulutuksenosa().getLaajuusMinimi())
//                        .append(" - ")
//                        .append(lapsi.getKoulutuksenosa().getLaajuusMaksimi())
//                        .append(" ")
//                        .append(messages.translate("docgen.laajuus.viikkoa", docBase.getKieli()));
//            }
//
//            addHeader(docBase, otsikkoBuilder.toString());
//
//            if (lapsi.isNaytaPerusteenTeksti() && lapsi.getPerusteteksti() != null && lapsi.getPerusteteksti().getTekstit() != null) {
//                addLokalisoituteksti(docBase, lapsi.getPerusteteksti(), "div");
//            }
//
//            if (lapsi.isNaytaPohjanTeksti() && lapsi.getPohjanTekstikappale() != null && lapsi.getPohjanTekstikappale().getTeksti() != null) {
//                addLokalisoituteksti(docBase, lapsi.getPohjanTekstikappale().getTeksti(), "div");
//            }
//
//            if (tekstiKappale.getTeksti() != null) {
//                addLokalisoituteksti(docBase, tekstiKappale.getTeksti(), "div");
//            } else {
//                addTeksti(docBase, "", "p"); // Sivutuksen kannalta v??ltt??m??t??n
//            }
//
//            switch (lapsi.getTyyppi()) {
//                case OSASUORITUSPOLKU:
//                case SUORITUSPOLKU:
//                    addSuorituspolku(docBase, lapsi, otsikkoBuilder.toString());
//                    break;
//                case TUTKINNONOSA:
//                    addTutkinnonosa(docBase, lapsi);
//                    break;
//                case OPINTOKOKONAISUUS:
//                    addOpintokokonaisuus(docBase, lapsi);
//                    break;
//                case LAAJAALAINENOSAAMINEN:
//                    addLaajaalainenOsaaminen(docBase, lapsi);
//                    break;
//                case KOULUTUKSENOSA:
//                    addKoulutuksenosa(docBase, lapsi);
//                    break;
//                case KOTO_LAAJAALAINENOSAAMINEN:
//                    addKotoLaajaAlainenOsaaminen(docBase, lapsi);
//                    break;
//                case KOTO_KIELITAITOTASO:
//                    addKotoKielitaitotaso(docBase, lapsi);
//                    break;
//                case KOTO_OPINTO:
//                    addKotoOpinto(docBase, lapsi);
//                    break;
//                default:
//                    break;
//            }
//
//            docBase.getGenerator().increaseDepth();
//
//            // Rekursiivisesti
//            addSisaltoviite(docBase, lapsi);
//
//            docBase.getGenerator().decreaseDepth();
//
//            docBase.getGenerator().increaseNumber();
//        }
    }

    private void addSuorituspolku(DokumenttiAmosaa docBase, SisaltoViiteExportDto viite, String suorituspolkuNimi) {
        SuorituspolkuExportDto suorituspolku = viite.getSuorituspolku();
        Map<UUID, SuorituspolkuRiviDto> suorituspolkuMap = new HashMap<>();

        // TODO: mist?? rivit?
//        if (suorituspolku != null) {
//            suorituspolku.getRivit().forEach(rivi -> {
//                if (rivi.getPiilotettu() != null && rivi.getPiilotettu()) {
//                    suorituspolkuMap.put(rivi.getRakennemoduuli(), rivi);
//                }
//            });
//        }

        PerusteKaikkiDto peruste = docBase.getPeruste();

        if (peruste != null) {
            peruste.getSuoritustavat().stream()
                    .filter(suoritustapaLaajaDto -> suoritustapaLaajaDto.getSuoritustapakoodi().equals(Suoritustapakoodi.OPS)
                            || suoritustapaLaajaDto.getSuoritustapakoodi().equals(Suoritustapakoodi.REFORMI))
                    .findAny()
                    .filter(suoritustapaLaajaDto -> suoritustapaLaajaDto.getRakenne() != null)
                    .ifPresent(suoritustapaLaajaDto -> {

                        // Luodaan muodostumistaulukko
                        Element taulukko = docBase.getDocument().createElement("table");
                        taulukko.setAttribute("border", "1");
                        Element otsikko = docBase.getDocument().createElement("caption");
                        otsikko.setTextContent(suorituspolkuNimi);
                        taulukko.appendChild(otsikko);
                        docBase.getBodyElement().appendChild(taulukko);
                        Element tbody = docBase.getDocument().createElement("tbody");
                        taulukko.appendChild(tbody);

                        suoritustapaLaajaDto.getRakenne().getOsat().stream()
                                .filter(dto -> dto.getTunniste() != null && !suorituspolkuMap.containsKey(dto.getTunniste()))
                                .filter(dto -> dto instanceof RakenneModuuliDto)
                                .map(dto -> (RakenneModuuliDto) dto)
                                .forEach(rakenneModuuliDto -> addSuorituspolkuOsa(
                                        docBase, rakenneModuuliDto, tbody, 1, suoritustapaLaajaDto, suorituspolkuMap));
                    });
        }
    }

    private void addSuorituspolkuOsa(DokumenttiAmosaa docBase,
                                     RakenneModuuliDto rakenneModuuliDto,
                                     Element tbody,
                                     int depth,
                                     SuoritustapaLaajaDto suoritustapaLaajaDto,
                                     Map<UUID, SuorituspolkuRiviDto> suorituspolkuMap) {

        Element tr = docBase.getDocument().createElement("tr");
        tr.setAttribute("bgcolor", "#F1F2F3");
        if (rakenneModuuliDto.getOsaamisala() != null) {
            tr.setAttribute("bgcolor", "#F3FAFD");
        }
        tbody.appendChild(tr);
        Element td = docBase.getDocument().createElement("td");
        tr.appendChild(td);
        td.setAttribute("class", "td" + depth);

        // Nimi
        StringBuilder nimiBuilder = new StringBuilder();
        nimiBuilder.append(getTextString(docBase, rakenneModuuliDto.getNimi()));
        if (rakenneModuuliDto.getOsaamisala() != null
                && rakenneModuuliDto.getOsaamisala().getOsaamisalakoodiArvo() != null) {
            nimiBuilder.append(" (");
            nimiBuilder.append(rakenneModuuliDto.getOsaamisala().getOsaamisalakoodiArvo());
            nimiBuilder.append(")");
        }
        addMuodostumisSaanto(docBase, rakenneModuuliDto.getMuodostumisSaanto(), nimiBuilder);
        addTeksti(docBase, nimiBuilder.toString(), "p", td);

        // Kuvaus
        if (rakenneModuuliDto.getKuvaus() != null) {
            addTeksti(docBase, getTextString(docBase, rakenneModuuliDto.getKuvaus()), "em", td);
        }

        rakenneModuuliDto.getOsat().forEach(lapsi -> {
            // Piilotettuja ei generoida PDF:????n
            if (suorituspolkuMap.containsKey(lapsi.getTunniste())) {
                SuorituspolkuRiviDto rivi = suorituspolkuMap.get(lapsi.getTunniste());
                if (rivi.getPiilotettu() != null && rivi.getPiilotettu()) {
                    return;
                }
            }

            if (lapsi instanceof RakenneModuuliDto) {
                RakenneModuuliDto lapsiDto = (RakenneModuuliDto) lapsi;
                addSuorituspolkuOsa(docBase, lapsiDto, tbody, depth + 1, suoritustapaLaajaDto, suorituspolkuMap);
            } else if (lapsi instanceof RakenneOsaDto) {
                RakenneOsaDto lapsiDto = (RakenneOsaDto) lapsi;
                if (lapsiDto.getTutkinnonOsaViite() != null) {
                    suoritustapaLaajaDto.getTutkinnonOsat().stream()
                            .filter(dto -> dto.getId().equals(lapsiDto.getTutkinnonOsaViite()))
                            .findAny()
                            .ifPresent(dto -> {
                                PerusteKaikkiDto peruste = docBase.getPeruste();
                                if (peruste != null) {
                                    peruste.getTutkinnonOsat().stream()
                                            .filter(tutkinnonOsaDto -> tutkinnonOsaDto.getId().equals(dto.getTutkinnonOsa()))
                                            .findAny()
                                            .ifPresent(tutkinnonOsaKaikkiDto -> addSuorituspolunTutkinnonOsa(
                                                    docBase, tutkinnonOsaKaikkiDto, dto, tbody, depth + 1));
                                }
                            });
                }
            }
        });

        // TODO: ei haeta en???? koodistosta
        // Lis??t????n tutkinnossa m????ritett??v?? rakenne osan aliosat
//        if (VIRTUAALINEN.equals(rakenneModuuliDto.getRooli())) {
//            if (suorituspolkuMap.containsKey(rakenneModuuliDto.getTunniste())) {
//                SuorituspolkuRiviDto rivi = suorituspolkuMap.get(rakenneModuuliDto.getTunniste());
//                Set<String> koodit = rivi.getKoodit();
//                if (koodit != null) {
//                    koodit.forEach(koodi -> {
//                        KoodistoKoodiDto koodistoKoodiDto = koodistoClient.getByUri(koodi);
//                        if (koodistoKoodiDto != null) {
//                            addSuorituspolunKoodiOsa(docBase, koodistoKoodiDto, tbody, depth + 1);
//                        }
//                    });
//                }
//            }
//        }
    }

    private void addMuodostumisSaanto(DokumenttiAmosaa docBase,
                                      MuodostumisSaantoDto muodostumisSaantoDto,
                                      StringBuilder builder) {
        if (muodostumisSaantoDto != null && muodostumisSaantoDto.getLaajuus() != null) {
            builder.append(" ");
            if (muodostumisSaantoDto.getLaajuus().getMinimi() != null
                    && muodostumisSaantoDto.getLaajuus().getMaksimi() != null
                    && muodostumisSaantoDto.getLaajuus().getMinimi()
                    .equals(muodostumisSaantoDto.getLaajuus().getMaksimi())) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMinimi());
            } else if (muodostumisSaantoDto.getLaajuus().getMinimi() != null
                    && muodostumisSaantoDto.getLaajuus().getMaksimi() != null) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMinimi());
                builder.append("-");
                builder.append(muodostumisSaantoDto.getLaajuus().getMaksimi());
            } else if (muodostumisSaantoDto.getLaajuus().getMinimi() != null) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMinimi());
            } else if (muodostumisSaantoDto.getLaajuus().getMaksimi() != null) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMaksimi());
            }

            if (muodostumisSaantoDto.getLaajuus().getYksikko() != null) {
                builder.append(" ");
                builder.append(muodostumisSaantoDto.getLaajuus().getYksikko());
            } else {
                builder.append(" ").append(messages.translate("docgen.laajuus.osp", docBase.getKieli()));
            }
        }
    }

    private void addSuorituspolunKoodiOsa(
            DokumenttiAmosaa docBase,
            KoodistoKoodiDto koodistoKoodiDto,
            Element tbody,
            int depth) {
        if (koodistoKoodiDto != null && koodistoKoodiDto.getMetadata() != null) {
            KoodistoMetadataDto[] metadata = koodistoKoodiDto.getMetadata();
            for (KoodistoMetadataDto metadataDto : metadata) {

                // Valitaan dokumentin kieli
                if (docBase.getKieli().toString().equals(metadataDto.getKieli().toLowerCase())) {

                    Element tr = docBase.getDocument().createElement("tr");
                    tbody.appendChild(tr);
                    Element td = docBase.getDocument().createElement("td");
                    tr.appendChild(td);
                    td.setAttribute("class", "td" + depth);

                    // Nimi
                    StringBuilder nimiBuilder = new StringBuilder();
                    nimiBuilder.append(metadataDto.getNimi());
                    if (koodistoKoodiDto.getKoodiArvo() != null) {
                        nimiBuilder.append(" (");
                        nimiBuilder.append(koodistoKoodiDto.getKoodiArvo());
                        nimiBuilder.append(")");
                    }

                    addTeksti(docBase, nimiBuilder.toString(), "p", td);

                }
            }
        }
    }

    private void addSuorituspolunTutkinnonOsa(DokumenttiAmosaa docBase,
                                              TutkinnonosaKaikkiDto tutkinnonOsaKaikkiDto,
                                              TutkinnonOsaViiteSuppeaDto tutkinnonOsaViiteSuppeaDto,
                                              Element tbody,
                                              int depth) {
        Element tr = docBase.getDocument().createElement("tr");
        tbody.appendChild(tr);
        Element td = docBase.getDocument().createElement("td");
        tr.appendChild(td);
        td.setAttribute("class", "td" + depth);

        // Nimi
        StringBuilder nimiBuilder = new StringBuilder();
        nimiBuilder.append(getTextString(docBase, tutkinnonOsaKaikkiDto.getNimi()));
        if (tutkinnonOsaKaikkiDto.getKoodiArvo() != null) {
            nimiBuilder.append(" (");
            nimiBuilder.append(tutkinnonOsaKaikkiDto.getKoodiArvo());
            nimiBuilder.append(")");
        }
        if (tutkinnonOsaViiteSuppeaDto.getLaajuus() != null) {
            nimiBuilder.append(" ");
            if (tutkinnonOsaViiteSuppeaDto.getLaajuus().stripTrailingZeros().scale() <= 0) {
                nimiBuilder.append(String.valueOf(tutkinnonOsaViiteSuppeaDto.getLaajuus().intValue()));
            } else {
                nimiBuilder.append(tutkinnonOsaViiteSuppeaDto.getLaajuus().toString());
            }
            nimiBuilder.append(" osp");
        }
        addTeksti(docBase, nimiBuilder.toString(), "p", td);
    }

    private void addTutkinnonosa(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        // TODO: t??m?? ei toimi koska TutkinnonosaBaseDto ei sis??ll?? kaikkia kentti??.
//        TutkinnonosaDto tutkinnonOsa = lapsi.getTosa();
//
//        switch (tutkinnonOsa.getTyyppi()) {
//            case OMA:
//                if (tutkinnonOsa.getOmatutkinnonosa() != null) {
//                    addOmaTutkinnonOsa(docBase, tutkinnonOsa.getOmatutkinnonosa());
//                }
//                break;
//            case PERUSTEESTA:
//                // TODO: onko relevantti boolean?
////                if (tutkinnonOsa.getPerusteentutkinnonosa() != null && docBase.getDokumentti().isPerusteenSisalto()) {
//                if (tutkinnonOsa.getPerusteentutkinnonosa() != null) {
//                    addPerusteenTutkinnonOsa(docBase, tutkinnonOsa.getPerusteentutkinnonosa());
//                }
//                break;
//            default:
//                break;
//        }
//
//        // Tutkinnon osan vapaat tekstit
//        tutkinnonOsa.getVapaat().forEach(vapaaTeksti -> {
//            addLokalisoituteksti(docBase, vapaaTeksti.getNimi(), "h5");
//            addLokalisoituteksti(docBase, vapaaTeksti.getTeksti(), "div");
//        });
//
//        // Osaamisen osoittaminen
//        if (tutkinnonOsa.getOsaamisenOsoittaminen() != null) {
//            addTeksti(docBase, messages.translate("docgen.osaamisen-osoittaminen", docBase.getKieli()), "h5");
//            addLokalisoituteksti(docBase, tutkinnonOsa.getOsaamisenOsoittaminen(), "div");
//        }
//
//        // Toteutukset
//        if (!tutkinnonOsa.getToteutukset().isEmpty()) {
//            addTeksti(docBase, messages.translate("docgen.toteutukset", docBase.getKieli()), "h5");
//
//            tutkinnonOsa.getToteutukset().forEach(toteutus -> {
//                addLokalisoituteksti(docBase, toteutus.getOtsikko(), "h6");
//                boolean toteutuksellaSisaltoa = false;
//
//                Element toteutusTaulukko = docBase.getDocument().createElement("table");
//                toteutusTaulukko.setAttribute("border", "1");
//
//                Element kooditTr = docBase.getDocument().createElement("tr");
//                Element kooditTd = docBase.getDocument().createElement("th");
//                kooditTr.appendChild(kooditTd);
//                Element koodit = docBase.getDocument().createElement("div");
//                kooditTd.appendChild(koodit);
//
//                for (String koodiUri : toteutus.getKoodit()) {
//                    KoodistoKoodiDto koodistoKoodiDto = koodistoClient.getByUri(koodiUri);
//                    KoodistoMetadataDto[] metadata = koodistoKoodiDto.getMetadata();
//                    for (KoodistoMetadataDto metadataDto : metadata) {
//                        // Valitaan haluttu kieli
//                        if (metadataDto.getKieli().toLowerCase().equals(docBase.getKieli().toString())) {
//                            addTeksti(docBase,
//                                    metadataDto.getNimi() + " (" + koodistoKoodiDto.getKoodiArvo() + ")",
//                                    "p",
//                                    koodit);
//                            toteutuksellaSisaltoa = true;
//                        }
//                    }
//                }
//
//                if (toteutuksellaSisaltoa) {
//                    DokumenttiTaulukko.addRow(docBase, toteutusTaulukko,
//                            messages.translate("docgen.liitetyt-koodit", docBase.getKieli()), true);
//                    toteutusTaulukko.appendChild(kooditTr);
//                }
//
//                TekstiosaDto tavatjaymparisto = toteutus.getTavatjaymparisto();
//                if (tavatjaymparisto != null && tavatjaymparisto.getTeksti() != null) {
//                    DokumenttiTaulukko.addRow(docBase,
//                            toteutusTaulukko,
//                            messages.translate("docgen.tavat-ja-ymparisto", docBase.getKieli()),
//                            true);
//
//                    Element tr = docBase.getDocument().createElement("tr");
//                    toteutusTaulukko.appendChild(tr);
//                    Element td = docBase.getDocument().createElement("th");
//                    tr.appendChild(td);
//                    addLokalisoituteksti(docBase, tavatjaymparisto.getTeksti(), "div", td);
//                    toteutuksellaSisaltoa = true;
//                }
//
//                TekstiosaDto arvioinnista = toteutus.getArvioinnista();
//                if (arvioinnista != null && arvioinnista.getTeksti() != null) {
//                    DokumenttiTaulukko.addRow(docBase,
//                            toteutusTaulukko,
//                            messages.translate("docgen.osaamisen-arvioinnista", docBase.getKieli()),
//                            true);
//
//                    Element tr = docBase.getDocument().createElement("tr");
//                    toteutusTaulukko.appendChild(tr);
//                    Element td = docBase.getDocument().createElement("th");
//                    tr.appendChild(td);
//                    addLokalisoituteksti(docBase, arvioinnista.getTeksti(), "div", td);
//
//                    toteutuksellaSisaltoa = true;
//                }
//
//                // Lis??t????n toteutuksen taulukko jos on sis??lt????
//                if (toteutuksellaSisaltoa) {
//                    docBase.getBodyElement().appendChild(toteutusTaulukko);
//                }
//
//                toteutus.getVapaat().forEach(vapaaTeksti -> {
//                    addLokalisoituteksti(docBase, vapaaTeksti.getNimi(), "h5");
//                    addLokalisoituteksti(docBase, vapaaTeksti.getTeksti(), "div");
//                });
//            });
//        }
    }

    private void addOmaTutkinnonOsa(DokumenttiAmosaa docBase, OmaTutkinnonosaDto omaTutkinnonosa) {

        // Laajuus
        if (omaTutkinnonosa.getLaajuus() != null) {
            addTeksti(docBase, messages.translate("docgen.laajuus", docBase.getKieli()), "h5");
            addTeksti(docBase, omaTutkinnonosa.getLaajuus().toString(), "div");
        }

        // Koodi
        if (omaTutkinnonosa.getKoodi() != null) {
            addTeksti(docBase, messages.translate("docgen.koodi", docBase.getKieli()), "h5");
            addTeksti(docBase, omaTutkinnonosa.getKoodi(), "div");
        }

        // Tavoitteet
        if (omaTutkinnonosa.getTavoitteet() != null) {
            addTeksti(docBase, messages.translate("docgen.tavoitteet", docBase.getKieli()), "h5");
            addLokalisoituteksti(docBase, omaTutkinnonosa.getTavoitteet(), "div");
        }


        // Ammattitaitovaatimukset
        if (omaTutkinnonosa.getAmmattitaitovaatimuksetLista().size() > 0) {
            addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");
            omaTutkinnonosa.getAmmattitaitovaatimuksetLista()
                    .forEach(ammattitaitovaatimuksenKohdealue -> addAmmattitaitovaatimuksenKohdealue(docBase, ammattitaitovaatimuksenKohdealue));
        }

        // Arviointi
        if (omaTutkinnonosa.getArviointi() != null) {
            addTeksti(docBase, messages.translate("docgen.arviointi", docBase.getKieli()), "h5");
            ArviointiDto arviointi = omaTutkinnonosa.getArviointi();
            arviointi.getArvioinninKohdealueet()
                    .forEach(arvioinninKohdealue -> addArvioinninKohdealue(docBase, arvioinninKohdealue));
        }

        // Ammattitaidon osoittamistavat
        if (omaTutkinnonosa.getAmmattitaidonOsoittamistavat() != null) {
            addTeksti(docBase, messages.translate("docgen.ammattitaidon-osoittamistavat", docBase.getKieli()), "h5");
            addLokalisoituteksti(docBase, omaTutkinnonosa.getAmmattitaidonOsoittamistavat(), "div");
        }
    }

    private void addAmmattitaitovaatimuksenKohdealue(DokumenttiAmosaa docBase,
                                                     AmmattitaitovaatimuksenKohdealueDto ammattitaitovaatimuksenKohdealue) {
        DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
        addVaatimuksenKohteet(docBase, ammattitaitovaatimuksenKohdealue.getVaatimuksenKohteet(), taulukko);
        taulukko.addToDokumentti(docBase);
    }

    private void addVaatimuksenKohteet(DokumenttiAmosaa docBase,
                                       List<AmmattitaitovaatimuksenKohdeDto> vaatimuksenKohteet,
                                       DokumenttiTaulukko taulukko) {
        StringBuilder vaatimuksenKohteetBuilder = new StringBuilder();
        vaatimuksenKohteet.forEach(ammattitaitovaatimuksenKohde -> {
            if (ammattitaitovaatimuksenKohde.getOtsikko() != null) {
                vaatimuksenKohteetBuilder.append("<h6>");
                vaatimuksenKohteetBuilder.append(getTextString(docBase, ammattitaitovaatimuksenKohde.getOtsikko()));
                vaatimuksenKohteetBuilder.append("</h6>");
            }
            if (ammattitaitovaatimuksenKohde.getSelite() != null) {
                vaatimuksenKohteetBuilder.append("<p>");
                vaatimuksenKohteetBuilder.append(ammattitaitovaatimuksenKohde.getSelite());
                vaatimuksenKohteetBuilder.append("</p>");
            }
            addVaatimukset(docBase, ammattitaitovaatimuksenKohde.getVaatimukset(), vaatimuksenKohteetBuilder);
        });
        DokumenttiRivi rivi = new DokumenttiRivi();
        rivi.addSarake(vaatimuksenKohteetBuilder.toString());
        taulukko.addRivi(rivi);
    }

    private void addVaatimukset(DokumenttiAmosaa docBase,
                                List<AmmattitaitovaatimusDto> ammattitaitovaatimukset,
                                StringBuilder vaatimuksenKohteetBuilder) {
        vaatimuksenKohteetBuilder.append("<ul>");
        ammattitaitovaatimukset.forEach(vaatimus -> {
            vaatimuksenKohteetBuilder.append("<li>");
            vaatimuksenKohteetBuilder.append(getTextString(docBase, vaatimus.getSelite()));
            if (vaatimus.getAmmattitaitovaatimusKoodi() != null) {
                vaatimuksenKohteetBuilder.append(" (");
                vaatimuksenKohteetBuilder.append(vaatimus.getAmmattitaitovaatimusKoodi());
                vaatimuksenKohteetBuilder.append(")");
            }
            vaatimuksenKohteetBuilder.append("</li>");
        });
        vaatimuksenKohteetBuilder.append("</ul>");
    }

    private void addArvioinninKohdealue(DokumenttiAmosaa docBase, ArvioinninKohdealueDto arvioinninKohdealue) {
        DokumenttiTaulukko arviointiTaulukko = new DokumenttiTaulukko();

        if (!getTextString(docBase, arvioinninKohdealue.getOtsikko()).equals("automaattinen")) {
            arviointiTaulukko.addOtsikkoSarake(getTextString(docBase, arvioinninKohdealue.getOtsikko()));
        }
        addArvioinninKohteet(docBase, arvioinninKohdealue.getArvioinninKohteet(), arviointiTaulukko);
        arviointiTaulukko.addToDokumentti(docBase);
    }

    private void addArvioinninKohteet(DokumenttiAmosaa docBase, List<ArvioinninKohdeDto> arvioinninKohteet, DokumenttiTaulukko arviointiTaulukko) {
        StringBuilder arvioinninKohteetBuilder = new StringBuilder();

        arvioinninKohteet.forEach(arvioinninKohde -> {
            if (arvioinninKohde.getOtsikko() != null) {
                arvioinninKohteetBuilder.append("<h6>");
                arvioinninKohteetBuilder.append(getTextString(docBase, arvioinninKohde.getOtsikko()));
                arvioinninKohteetBuilder.append("</h6>");
            }
            if (arvioinninKohde.getSelite() != null) {
                arvioinninKohteetBuilder.append("<p>");
                arvioinninKohteetBuilder.append(arvioinninKohde.getSelite());
                arvioinninKohteetBuilder.append("</p>");
            }
            // Add kohteen taulukko
            addOsaamistasonKriteerit(docBase, arvioinninKohde.getOsaamistasonKriteerit(), arvioinninKohteetBuilder);
        });


        DokumenttiRivi rivi = new DokumenttiRivi();
        rivi.addSarake(arvioinninKohteetBuilder.toString());
        arviointiTaulukko.addRivi(rivi);
    }

    private void addOsaamistasonKriteerit(DokumenttiAmosaa docBase,
                                          Set<OsaamistasonKriteeriDto> osaamistasonKriteerit,
                                          StringBuilder arvioinninKohteetBuilder) {
        DokumenttiTaulukko taulukko = new DokumenttiTaulukko();

        osaamistasonKriteerit.stream()
                .filter(o -> o.getOsaamistaso() != null)
                .sorted(Comparator.comparing(o -> o.getOsaamistaso().getId()))
                .forEach(osaamistasonKriteeri -> {
                    DokumenttiRivi rivi = new DokumenttiRivi();
                    // TODO: fix getOtsikko
//                    rivi.addSarake(getTextString(docBase, osaamistasonKriteeri.getOsaamistaso().getOtsikko()));

                    StringBuilder kriteeritBuilder = new StringBuilder();
                    kriteeritBuilder.append("<ul>");
                    osaamistasonKriteeri.getKriteerit().forEach(kriteeri -> {
                        kriteeritBuilder.append("<li>");
                        kriteeritBuilder.append(getTextString(docBase, kriteeri));
                        kriteeritBuilder.append("</li>");
                    });
                    kriteeritBuilder.append("</ul>");
                    rivi.addSarake(kriteeritBuilder.toString());

                    taulukko.addRivi(rivi);
                });

        arvioinninKohteetBuilder.append(taulukko);
    }

    private void addPerusteenTutkinnonOsa(DokumenttiAmosaa docBase, Long perusteenTutkinnonosaId) {
        PerusteKaikkiDto peruste = docBase.getPeruste();
        if (peruste == null) {
            return;
        }

        Optional<TutkinnonosaKaikkiDto> optPerusteenTutkinnonosa = peruste.getTutkinnonOsat().stream()
                .filter(dto -> dto.getId().equals(perusteenTutkinnonosaId))
                .findFirst();

        if (optPerusteenTutkinnonosa.isPresent()) {
            TutkinnonosaKaikkiDto perusteenTutkinnonosa = optPerusteenTutkinnonosa.get();

            if (perusteenTutkinnonosa.getTyyppi().equals(TutkinnonOsaTyyppi.NORMAALI)) {

                // Tavoitteet
                if (perusteenTutkinnonosa.getTavoitteet() != null) {
                    addTeksti(docBase, messages.translate("docgen.tavoitteet", docBase.getKieli()), "h5");
                    addLokalisoituteksti(docBase, perusteenTutkinnonosa.getTavoitteet(), "div");
                }


                // Ammattitaitovaatimukset
                if (perusteenTutkinnonosa.getAmmattitaitovaatimuksetLista() != null) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");
                    perusteenTutkinnonosa.getAmmattitaitovaatimuksetLista().forEach(dto -> {
                        AmmattitaitovaatimuksenKohdealueDto ammattitaitovaatimuksenKohdealue = objectMapper.convertValue(dto, AmmattitaitovaatimuksenKohdealueDto.class);
                        addAmmattitaitovaatimuksenKohdealue(docBase, ammattitaitovaatimuksenKohdealue);
                    });
                } else if (perusteenTutkinnonosa.getAmmattitaitovaatimukset() != null) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");
                    addLokalisoituteksti(docBase, perusteenTutkinnonosa.getAmmattitaitovaatimukset(), "div");
                }

                // Arviointi
                if (perusteenTutkinnonosa.getArviointi() != null) {
                    addTeksti(docBase, messages.translate("docgen.arviointi", docBase.getKieli()), "h5");
                    ArviointiDto arviointiDto = perusteenTutkinnonosa.getArviointi();

                    arviointiDto.getArvioinninKohdealueet().forEach(arvioinninKohdealueDto -> {
                        ArvioinninKohdealueDto arvioinninKohdealue = objectMapper.convertValue(arvioinninKohdealueDto, ArvioinninKohdealueDto.class);

                        for (int i = 0; i < arvioinninKohdealue.getArvioinninKohteet().size(); i++) {
                            ArvioinninKohdeDto arvioinninKohde = arvioinninKohdealue.getArvioinninKohteet().get(i);
                            ArvioinninKohdeDto arvioinninKohdeDto = arvioinninKohdealueDto.getArvioinninKohteet().get(i);
                            Reference arviointiasteikkoRef = arvioinninKohdeDto.getArviointiasteikko();
                            if (arviointiasteikkoRef != null) {
                                Long arviointiasteikkoId = arviointiasteikkoRef.getIdLong();
                                ArviointiAsteikkoDto arviointiasteikko = eperusteetService.getArviointiasteikko(arviointiasteikkoId);
                                // TODO: korjaa referenssi
//                                arvioinninKohde.setArviointiasteikko(arviointiasteikko);

                                Set<OsaamistasonKriteeriDto> osaamistasonKriteerit = arvioinninKohdeDto.getOsaamistasonKriteerit().stream()
                                        .sorted(Comparator.comparing(osaamistasonKriteeriDto -> osaamistasonKriteeriDto.getOsaamistaso().getIdLong()))
                                        .map(osaamistasonKriteeriDto -> {
                                            Optional<OsaamistasoDto> optOsaamistaso = arviointiasteikko.getOsaamistasot().stream()
                                                    .filter(o -> o.getId().equals(osaamistasonKriteeriDto.getOsaamistaso().getIdLong()))
                                                    .findFirst();

                                            OsaamistasonKriteeriDto osaamistasonKriteeri = objectMapper.convertValue(osaamistasonKriteeriDto, OsaamistasonKriteeriDto.class);

                                            if (optOsaamistaso.isPresent()) {
                                                OsaamistasoDto osaamistaso = optOsaamistaso.get();
                                                // TODO: korjaa referenssi
//                                                osaamistasonKriteeri.setOsaamistaso(osaamistaso);
                                            }
                                            return osaamistasonKriteeri;
                                        }).collect(Collectors.toSet());

                                arvioinninKohde.setOsaamistasonKriteerit(osaamistasonKriteerit);
                            }
                        }
                        addArvioinninKohdealue(docBase, arvioinninKohdealue);
                    });
                }

                // Ammattitaidon osoittamistavat
                if (perusteenTutkinnonosa.getAmmattitaidonOsoittamistavat() != null) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaidon-osoittamistavat", docBase.getKieli()), "h5");
                    addLokalisoituteksti(docBase, perusteenTutkinnonosa.getAmmattitaidonOsoittamistavat(), "div");
                }

                // Valma/Telma-sis??lt??
                if (perusteenTutkinnonosa.getValmaTelmaSisalto() != null) {
                    addValmatelmaSisalto(docBase, perusteenTutkinnonosa.getValmaTelmaSisalto());
                }

            } else if (TutkinnonOsaTyyppi.isTutke(perusteenTutkinnonosa.getTyyppi())) {
                if (!ObjectUtils.isEmpty(perusteenTutkinnonosa.getOsaAlueet())) {
                    perusteenTutkinnonosa.getOsaAlueet().stream()
                            .filter(osaAlue -> osaAlue.getNimi() != null)
                            .forEach(osaAlue -> {
                                // Nimi
                                addTeksti(docBase, getTextString(docBase, osaAlue.getNimi()), "h5");

                                // Kuvaus
                                if (osaAlue.getKuvaus() != null) {
                                    addTeksti(docBase, getTextString(docBase, osaAlue.getKuvaus()), "div");
                                }

                                // Osaamistavoitteet
                                if (!ObjectUtils.isEmpty(osaAlue.getOsaamistavoitteet())) {
                                    osaAlue.getOsaamistavoitteet().forEach(osaamistavoite -> {

                                        addTeksti(docBase, getTextString(docBase, osaamistavoite.getNimi()), "h5");

                                        String otsikkoAvain = osaamistavoite.isPakollinen() ? "docgen.tutke2.pakolliset_osaamistavoitteet.title"
                                                : "docgen.tutke2.valinnaiset_osaamistavoitteet.title";
                                        String otsikko = messages.translate(otsikkoAvain, docBase.getKieli());
                                        addTeksti(docBase, otsikko, "h5");

                                        addTeksti(docBase, getTextString(docBase, osaamistavoite.getTavoitteet()), "div");

                                        // Arviointi
                                        addTeksti(docBase, messages.translate("docgen.tutke2.arvioinnin_kohteet.title", docBase.getKieli()), "h6");
                                        ArviointiDto arviointi = osaamistavoite.getArviointi();
                                        arviointi.getArvioinninKohdealueet().forEach(dto -> addArvioinninKohdealue(docBase, dto));

                                        // Tunnustaminen
                                        addTeksti(docBase, messages.translate("docgen.tutke2.tunnustaminen.title", docBase.getKieli()), "h6");
                                        addTeksti(docBase, getTextString(docBase, osaamistavoite.getTunnustaminen()), "div");

                                        // Ammattitaitovaatimukset
                                        List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimukset = osaamistavoite.getAmmattitaitovaatimuksetLista();
                                        ammattitaitovaatimukset.forEach(dto -> addAmmattitaitovaatimuksenKohdealue(docBase, objectMapper.convertValue(dto, AmmattitaitovaatimuksenKohdealueDto.class)));
                                    });
                                }
                                if (osaAlue.getValmaTelmaSisalto() != null) {
                                    addValmatelmaSisalto(docBase, osaAlue.getValmaTelmaSisalto());
                                }
                            });
                }
            }
        }
    }

    private void addOpintokokonaisuus(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        OpintokokonaisuusDto opintokokonaisuus = lapsi.getOpintokokonaisuus();

        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getKuvaus()), "div");
        addTeksti(docBase, messages.translate("docgen.opetuksen-tavoitteet.title", docBase.getKieli()), "h5");

        if (opintokokonaisuus.getTavoitteidenKuvaus() != null) {
            addTeksti(docBase, messages.translate("docgen.tavoitteiden-kuvaus", docBase.getKieli()), "h6");
            addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getTavoitteidenKuvaus()), "div");
        }

        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getOpetuksenTavoiteOtsikko()), "h6");
        Element tavoitteetEl = docBase.getDocument().createElement("ul");

        opintokokonaisuus.getTavoitteet().forEach(tavoite -> {
            Element tavoiteEl = docBase.getDocument().createElement("li");
            String rivi = getTextString(docBase, tavoite.getTavoite());
            tavoiteEl.setTextContent(rivi);
            tavoitteetEl.appendChild(tavoiteEl);
        });
        docBase.getBodyElement().appendChild(tavoitteetEl);

        addTeksti(docBase, messages.translate("docgen.keskeiset-sisallot.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getKeskeisetSisallot()), "div");
        addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");

        if (opintokokonaisuus.getArvioinninKuvaus() != null) {
            addTeksti(docBase, messages.translate("docgen.arvioinnin-kuvaus", docBase.getKieli()), "h6");
            addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getArvioinninKuvaus()), "div");
        }

        addTeksti(docBase, messages.translate("docgen.osaamisen-arvioinnin-kriteerit", docBase.getKieli()), "h6");
        Element arvioinnitEl = docBase.getDocument().createElement("ul");

        opintokokonaisuus.getArvioinnit().forEach(arviointi -> {
            Element arviointiEl = docBase.getDocument().createElement("li");
            String rivi = getTextString(docBase, arviointi.getArviointi());
            arviointiEl.setTextContent(rivi);
            arvioinnitEl.appendChild(arviointiEl);
        });
        docBase.getBodyElement().appendChild(arvioinnitEl);
    }

    private void addLaajaalainenOsaaminen(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        addTeksti(docBase, getTextString(docBase, lapsi.getTuvaLaajaAlainenOsaaminen().getTeksti()), "div");
    }

    private void addKoulutuksenosa(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        KoulutuksenOsaDto koulutuksenOsaDto = lapsi.getKoulutuksenosa();
        addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getKuvaus()), "div");

        addTeksti(docBase, messages.translate("docgen.tavoitteet.title", docBase.getKieli()), "h5");
        Element tavoitteetEl = docBase.getDocument().createElement("ul");
        Stream.concat(
                Stream.of(koulutuksenOsaDto.getTavoitteet()),
                Stream.of(koulutuksenOsaDto.getPaikallinenTarkennus() != null ? koulutuksenOsaDto.getPaikallinenTarkennus().getTavoitteet() : Collections.<LokalisoituTekstiDto>emptyList()))
                .flatMap(Collection::stream)
                .forEach(tavoite -> {
                    Element tavoiteEl = docBase.getDocument().createElement("li");
                    String rivi = getTextString(docBase, tavoite);
                    tavoiteEl.setTextContent(rivi);
                    tavoitteetEl.appendChild(tavoiteEl);
                });
        docBase.getBodyElement().appendChild(tavoitteetEl);

        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getPaikallinenTarkennus().getTavoitteetKuvaus()), "div");
        }

        if (!koulutuksenOsaDto.getPaikallinenTarkennus().getLaajaalaisetosaamiset().isEmpty()) {
            addTeksti(docBase, messages.translate("docgen.laaja-alainen-osaaminen.title", docBase.getKieli()), "h5");

            koulutuksenOsaDto.getPaikallinenTarkennus().getLaajaalaisetosaamiset().forEach(lao -> {
                addTeksti(docBase, getTextString(docBase, lao.getNimi()), "h6");
                addTeksti(docBase, getTextString(docBase, lao.getLaajaAlaisenOsaamisenKuvaus()), "div");
            });
        }

        addTeksti(docBase, messages.translate("docgen.keskeinen-sisalto.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getKeskeinenSisalto()), "div");

        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getPaikallinenTarkennus().getKeskeinenSisalto()), "div");
        }

        addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getArvioinninKuvaus()), "div");

        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getPaikallinenTarkennus().getArvioinninKuvaus()), "div");
        }

        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null && !CollectionUtils.isEmpty(koulutuksenOsaDto.getPaikallinenTarkennus().getKoulutuksenJarjestajat())) {
            addTeksti(docBase, messages.translate("docgen.koulutuksen-jarjestajat.title", docBase.getKieli()), "h5");

            koulutuksenOsaDto.getPaikallinenTarkennus().getKoulutuksenJarjestajat().forEach(koulutuksenJarjestajaDto -> {
                addTeksti(docBase, getTextString(docBase, koulutuksenJarjestajaDto.getNimi()), "h5");

                addTeksti(docBase, messages.translate("docgen.toteutusuunnitelman-tai-koulutuksen-jarjestajan-verkkosivut.title", docBase.getKieli()), "h6");

                Element linkkiDiv = docBase.getDocument().createElement("div");
                Element koulutuksenjarjestajanUrl = docBase.getDocument().createElement("a");
                koulutuksenjarjestajanUrl.setTextContent(getTextString(docBase, koulutuksenJarjestajaDto.getUrl()));
                koulutuksenjarjestajanUrl.setAttribute("href", getTextString(docBase, koulutuksenJarjestajaDto.getUrl()));
                linkkiDiv.appendChild(koulutuksenjarjestajanUrl);
                docBase.getBodyElement().appendChild(linkkiDiv);

                addTeksti(docBase, messages.translate("docgen.kaytannon-toteutus.title", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, koulutuksenJarjestajaDto.getKuvaus()), "div");
            });
        }
    }

    private void addKotoLaajaAlainenOsaaminen(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        KotoLaajaAlainenOsaaminenDto perusteenOsaDto = docBase.getOpetussuunnitelma().getSisalto().getKotoLaajaAlainenOsaaminen().getPerusteenOsa();
        addTeksti(docBase, getTextString(docBase, perusteenOsaDto.getYleiskuvaus()), "div");

        perusteenOsaDto.getOsaamisAlueet().forEach(osaamisalue -> {
            addTeksti(docBase, getTextString(docBase, new LokalisoituTekstiDto(osaamisalue.getKoodi().getNimi())), "h6");
            addTeksti(docBase, getTextString(docBase, osaamisalue.getKuvaus()), "div");
        });

        addTeksti(docBase, messages.translate("docgen.laaja-alaisen-osaamisen-paikallinen-tarkennus", docBase.getKieli()), "h6");
        addTeksti(docBase, getTextString(docBase, lapsi.getKotoLaajaAlainenOsaaminen().getTeksti()), "div");
    }

    private void addKotoKielitaitotaso(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        KotoKielitaitotasoExportDto perusteenOsaDto = docBase.getOpetussuunnitelma().getSisalto().getKotoKielitaitotaso();
        addTeksti(docBase, getTextString(docBase, perusteenOsaDto.getKuvaus()), "div");

        Map<String, fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto> taitotasoMap = lapsi.getKotoKielitaitotaso().getTaitotasot().stream()
                .collect(Collectors.toMap((fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto::getKoodiUri), (taitotaso -> taitotaso)));
        addKotoTaitotasot(docBase, taitotasoMap, perusteenOsaDto.getTaitotasot(), "docgen.tavoitteet.title");

        addKotoTaitotasoLaajaAlaisetOsaamiset(docBase, lapsi.getKotoKielitaitotaso().getLaajaAlaisetOsaamiset());
    }

    private void addKotoOpinto(DokumenttiAmosaa docBase, SisaltoViiteExportDto lapsi) {
        KotoOpintoExportDto perusteenOsaDto = docBase.getOpetussuunnitelma().getSisalto().getKotoOpinto();
        addTeksti(docBase, getTextString(docBase, perusteenOsaDto.getKuvaus()), "div");

        Map<String, fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto> taitotasoMap = lapsi.getKotoOpinto().getTaitotasot().stream()
                .collect(Collectors.toMap(fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto::getKoodiUri, taitotaso -> taitotaso));
        addKotoTaitotasot(docBase, taitotasoMap, perusteenOsaDto.getTaitotasot(), "docgen.tavoitteet-ja-sisallot.title");

        addKotoTaitotasoLaajaAlaisetOsaamiset(docBase, lapsi.getKotoOpinto().getLaajaAlaisetOsaamiset());
    }

    private void addKotoTaitotasoLaajaAlaisetOsaamiset(DokumenttiAmosaa docBase, List<KotoTaitotasoLaajaAlainenOsaaminenDto> laajaAlaisetOsaamiset) {
        List<SisaltoViiteDto> laajaAlaisetViitteet = amosaaService.getSisaltoviitteenTyypilla(docBase.getOpetussuunnitelma().getKoulutustoimija().getId(), docBase.getOpetussuunnitelma().getId(), SisaltoTyyppi.KOTO_LAAJAALAINENOSAAMINEN);
        Map<String, KotoLaajaAlaisenOsaamisenAlueDto> perusteenLaot = laajaAlaisetViitteet.stream()
                .map(laoViite -> docBase.getOpetussuunnitelma().getSisalto().getKotoLaajaAlainenOsaaminen())
                .map(KotoLaajaAlainenOsaaminenExportDto::getPerusteenOsa)
                .map(KotoLaajaAlainenOsaaminenDto::getOsaamisAlueet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap((lao -> lao.getKoodi().getUri()), (lao -> lao)));

        if (!laajaAlaisetOsaamiset.isEmpty()) {
            addTeksti(docBase, messages.translate("docgen.laaja-alainen-osaaminen.title", docBase.getKieli()), "h5");
        }

        laajaAlaisetOsaamiset.forEach(lao -> {
            KotoLaajaAlaisenOsaamisenAlueDto perusteenLao = perusteenLaot.get(lao.getKoodiUri());
            addTeksti(docBase, getTextString(docBase, new LokalisoituTekstiDto(perusteenLao.getKoodi().getNimi())), "h6");
            addTeksti(docBase, getTextString(docBase, perusteenLao.getKuvaus()), "div");

            addTeksti(docBase, getTextString(docBase, lao.getTeksti()), "div");
        });
    }

    private void addKotoTaitotasot(DokumenttiAmosaa docBase,
                                   Map<String, fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto> taitotasoMap,
                                   List<fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto> taitotasot,
                                   String tavoiteTitle) {

        taitotasot.forEach(taitotaso -> {
            addTeksti(docBase, getTextString(docBase, new LokalisoituTekstiDto(taitotaso.getNimi().getNimi())), "h5");

            String tavoitteet = getTextString(docBase, taitotaso.getTavoitteet());
            addTextWithTopic(tavoitteet, tavoiteTitle, docBase);

            if (taitotasoMap.get(taitotaso.getNimi().getUri()) != null) {
                fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto opsTaitotaso = taitotasoMap.get(taitotaso.getNimi().getUri());

                if (opsTaitotaso.getTavoiteTarkennus() != null) {
                    String tavoiteTarkennus = getTextString(docBase, opsTaitotaso.getTavoiteTarkennus());
                    addTextWithTopic(tavoiteTarkennus, "docgen.tavoitteiden-paikallinen-tarkennus.title", docBase);
                }

                if (opsTaitotaso.getSisaltoTarkennus() != null) {
                    String sisaltoTarkennus = getTextString(docBase, opsTaitotaso.getSisaltoTarkennus());
                    addTextWithTopic(sisaltoTarkennus, "docgen.sisaltojen-paikallinen-tarkennus.title", docBase);
                }
            }

            String kielenkayttotarkoitus = getTextString(docBase, taitotaso.getKielenkayttotarkoitus());
            String aihealueet = getTextString(docBase, taitotaso.getAihealueet());
            String viestintataidot = getTextString(docBase, taitotaso.getViestintataidot());
            String opiskelijantaidot = getTextString(docBase, taitotaso.getOpiskelijantaidot());

            String opiskelijanTyoelamataidot = getTextString(docBase, taitotaso.getOpiskelijanTyoelamataidot());
            String suullinenVastaanottaminen = getTextString(docBase, taitotaso.getSuullinenVastaanottaminen());
            String suullinenTuottaminen = getTextString(docBase, taitotaso.getSuullinenTuottaminen());
            String vuorovaikutusJaMediaatio = getTextString(docBase, taitotaso.getVuorovaikutusJaMediaatio());

            if (!ObjectUtils.isEmpty(kielenkayttotarkoitus)
                    || !ObjectUtils.isEmpty(aihealueet)
                    || !ObjectUtils.isEmpty(viestintataidot)
                    || !ObjectUtils.isEmpty(opiskelijantaidot)
                    || !ObjectUtils.isEmpty(opiskelijanTyoelamataidot)
                    || !ObjectUtils.isEmpty(suullinenVastaanottaminen)
                    || !ObjectUtils.isEmpty(suullinenTuottaminen)
                    || !ObjectUtils.isEmpty(vuorovaikutusJaMediaatio)) {
                addTeksti(docBase, messages.translate("docgen.keskeiset-sisallot.title", docBase.getKieli()), "h5");
            }

            addTextWithTopic(kielenkayttotarkoitus, "docgen.kielenkayttotarkoitus.title", docBase);
            addTextWithTopic(aihealueet, "docgen.aihealueet.title", docBase);
            addTextWithTopic(viestintataidot, "docgen.viestintataidot.title", docBase);
            addTextWithTopic(opiskelijantaidot, "docgen.opiskelijantaidot.title", docBase);

            addTextWithTopic(opiskelijanTyoelamataidot, "docgen.opiskelijan_tyoelamataidot.title", docBase);
            addTextWithTopic(suullinenVastaanottaminen, "docgen.suullinen_vastaanottaminen.title", docBase);
            addTextWithTopic(suullinenTuottaminen, "docgen.suullinen_tuottaminen.title", docBase);
            addTextWithTopic(vuorovaikutusJaMediaatio, "docgen.vuorovaikutus_ja_mediaatio.title", docBase);
        });
    }

    private void addTextWithTopic(String text, String translationKey, DokumenttiAmosaa docBase) {
        if (!ObjectUtils.isEmpty(text)) {
            addTeksti(docBase, messages.translate(translationKey, docBase.getKieli()), "h6");
            addTeksti(docBase, text, "div");
        }
    }

    private void addValmatelmaSisalto(DokumenttiAmosaa docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        addValmaOsaamistavoitteet(docBase, valmaTelmaSisalto.getOsaamistavoite());
        addValmaArviointi(docBase, valmaTelmaSisalto);
    }

    private void addValmaOsaamistavoitteet(DokumenttiAmosaa docBase, List<OsaamisenTavoiteDto> OsaamisenTavoiteet) {
        if (ObjectUtils.isEmpty(OsaamisenTavoiteet)) {
            return;
        }

        addTeksti(docBase, messages.translate("docgen.valma.osaamistavoitteet.title", docBase.getKieli()), "h5");

        for (OsaamisenTavoiteDto osaamisenTavoite : OsaamisenTavoiteet) {
            if (osaamisenTavoite.getNimi() != null) {
                addTeksti(docBase, getTextString(docBase, osaamisenTavoite.getNimi()), "h6");
            }

            if (osaamisenTavoite.getKohde() != null) {
                addTeksti(docBase, getTextString(docBase, osaamisenTavoite.getKohde()), "div");
            }

            Element lista = docBase.getDocument().createElement("ul");
            docBase.getBodyElement().appendChild(lista);
            osaamisenTavoite.getTavoitteet().forEach(tavoite -> {
                Element alkio = docBase.getDocument().createElement("li");
                alkio.setTextContent(getTextString(docBase, tavoite));
                lista.appendChild(alkio);
            });

            if (osaamisenTavoite.getSelite() != null) {
                addTeksti(docBase, getTextString(docBase, osaamisenTavoite.getSelite()), "div");
            }
        }
    }

    private void addValmaArviointi(DokumenttiAmosaa docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        if (valmaTelmaSisalto.getOsaamisenarviointi() != null
                || valmaTelmaSisalto.getOsaamisenarviointiTekstina() != null) {

            addTeksti(docBase, messages.translate("docgen.valma.osaamisenarviointi.title", docBase.getKieli()), "h5");

            if (valmaTelmaSisalto.getOsaamisenarviointi() != null) {
                if (valmaTelmaSisalto.getOsaamisenarviointi().getKohde() != null) {
                    addTeksti(docBase,
                            getTextString(docBase, valmaTelmaSisalto.getOsaamisenarviointi().getKohde()),
                            "div");
                }

                if (!ObjectUtils.isEmpty(valmaTelmaSisalto.getOsaamisenarviointi().getTavoitteet())) {
                    Element lista = docBase.getDocument().createElement("ul");
                    docBase.getBodyElement().appendChild(lista);

                    valmaTelmaSisalto.getOsaamisenarviointi().getTavoitteet().forEach(tavoite -> {
                        Element alkio = docBase.getDocument().createElement("li");
                        alkio.setTextContent(getTextString(docBase, tavoite));
                        lista.appendChild(alkio);
                    });
                }
            }

            if (valmaTelmaSisalto.getOsaamisenarviointiTekstina() != null) {
                addTeksti(docBase,
                        valmaTelmaSisalto.getOsaamisenarviointiTekstina().getTekstit().get(docBase.getKieli()),
                        "div");
            }
        }
    }

    private void buildFootnotes(DokumenttiAmosaa docBase) {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            XPathExpression expression = xpath.compile("//abbr");
            NodeList list = (NodeList) expression.evaluate(docBase.getDocument(), XPathConstants.NODESET);

            int noteNumber = 1;
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                Node node = list.item(i);
                if (node.getAttributes() != null & node.getAttributes().getNamedItem("data-viite") != null) {
                    String avain = node.getAttributes().getNamedItem("data-viite").getNodeValue();

                    if (docBase.getOpetussuunnitelma() != null && docBase.getOpetussuunnitelma().getId() != null) {
                        TermiDto termi = dokumenttiUtilService.getTermiFromExternalService(docBase.getOpetussuunnitelma().getKoulutustoimija().getId(), avain, docBase.getGeneratorData().getTyyppi());

                        if (termi != null && termi.getAlaviite() != null && termi.getAlaviite() && termi.getSelitys() != null) {
                            element.setAttribute("number", String.valueOf(noteNumber));

                            LokalisoituTekstiDto tekstiDto = termi.getSelitys();
                            String selitys = getTextString(docBase, tekstiDto).replaceAll("<[^>]+>", "");
                            element.setAttribute("text", selitys);
                            noteNumber++;
                        }
                    }
                }
            }
        } catch (XPathExpressionException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
