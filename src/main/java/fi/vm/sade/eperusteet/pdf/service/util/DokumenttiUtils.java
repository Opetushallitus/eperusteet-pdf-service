package fi.vm.sade.eperusteet.pdf.service.util;

import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.LokalisoituTeksti;
import fi.vm.sade.eperusteet.pdf.domain.TekstiPalanen;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.validointi.ValidHtml;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.exception.SyntaxValidationException;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.apache.pdfbox.preflight.utils.ByteArrayDataSource;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Map;

public class DokumenttiUtils {
    private static final int MAX_TIME_IN_MINUTES = 5;

    //FIX THIS LokalisoituTeksti -> LokalisoituTekstiDto
//    public static void addLokalisoituteksti(DokumenttiBase docBase, LokalisoituTekstiDto lTekstiDto, String tagi) {
//        if (lTekstiDto != null) {
//            addLokalisoituteksti(docBase, docBase.getMapper().map(lTekstiDto, LokalisoituTeksti.class), tagi);
//        }
//    }

    public static void addLokalisoituteksti(DokumenttiBase docBase, LokalisoituTeksti lTeksti, String tagi) {
        if (lTeksti != null && lTeksti.getTeksti() != null && lTeksti.getTeksti() != null) {
            String teksti = lTeksti.getTeksti();
            teksti = "<" + tagi + ">" + unescapeHtml5(teksti) + "</" + tagi + ">";

            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(teksti));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

            docBase.getBodyElement().appendChild(docBase.getDocument().importNode(node, true));
        }
    }

    public static void addTeksti(DokumenttiBase docBase, String teksti, String tagi) {
        addTeksti(docBase, teksti, tagi, docBase.getBodyElement());
    }

    public static void addTeksti(DokumenttiBase docBase, String teksti, String tagi, Element element) {
        if (teksti != null) {

            teksti = unescapeHtml5(teksti);
            teksti = "<" + tagi + ">" + teksti + "</" + tagi + ">";

            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(teksti));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

            element.appendChild(docBase.getDocument().importNode(node, true));
        }
    }

    public static String tagTeksti(String teksti, String tagi) {
        if (teksti != null) {

            teksti = unescapeHtml5(teksti);
            return "<" + tagi + ">" + teksti + "</" + tagi + ">";
        }

        return "<" + tagi + "></" + tagi + ">";
    }

    public static void addHeader(DokumenttiPeruste docBase, String text) {
        if (text != null) {
            Element header = docBase.getDocument().createElement("h" + docBase.getGenerator().getDepth());
            header.setAttribute("number", docBase.getGenerator().generateNumber());
            header.appendChild(docBase.getDocument().createTextNode(unescapeHtml5(text)));
            docBase.getBodyElement().appendChild(header);
        }
    }

    public static String getTextString(DokumenttiBase docBase, Map<String, String> teksti) {
        Kieli kieli = docBase.getKieli();
        String result = null;
        if (teksti != null) {
            result = teksti.get(kieli.toString());
        }
        return result != null ? result : "";
    }

    public static String getTextString(Kieli kieli, TekstiPalanen tekstiPalanen) {
        if (tekstiPalanen != null
                && tekstiPalanen.getTeksti() != null
                && tekstiPalanen.getTeksti().containsKey(kieli)
                && tekstiPalanen.getTeksti().get(kieli) != null) {
            return unescapeHtml5(tekstiPalanen.getTeksti().get(kieli));
        } else {
            return "";
        }
    }

    public static String getTextString(DokumenttiBase docBase, TekstiPalanen tekstiPalanen) {
        if (tekstiPalanen != null
                && tekstiPalanen.getTeksti() != null
                && tekstiPalanen.getTeksti().containsKey(docBase.getKieli())
                && tekstiPalanen.getTeksti().get(docBase.getKieli()) != null) {
            return unescapeHtml5(tekstiPalanen.getTeksti().get(docBase.getKieli()));
        } else {
            return "";
        }
    }

    public static String getTextString(DokumenttiBase docBase, LokalisoituTekstiDto lokalisoituTekstiDto) {
        if (lokalisoituTekstiDto != null && lokalisoituTekstiDto.getTekstit() != null && docBase.getKieli() != null
                && lokalisoituTekstiDto.getTekstit().containsKey(docBase.getKieli())
                && lokalisoituTekstiDto.getTekstit().get(docBase.getKieli()) != null) {
            return unescapeHtml5(lokalisoituTekstiDto.getTekstit().get(docBase.getKieli()));
        } else {
            return "";
        }
    }

    public static String getTextString(Kieli kieli, LokalisoituTekstiDto lokalisoituTekstiDto) {
        if (lokalisoituTekstiDto != null && lokalisoituTekstiDto.getTekstit() != null && kieli != null
                && lokalisoituTekstiDto.getTekstit().containsKey(kieli)
                && lokalisoituTekstiDto.getTekstit().get(kieli) != null) {
            return unescapeHtml5(lokalisoituTekstiDto.getTekstit().get(kieli));
        } else {
            return "";
        }
    }

    public static String getTextString(DokumenttiBase docBase, LokalisoituTeksti lokalisoituTeksti) {
        if (lokalisoituTeksti == null || lokalisoituTeksti.getTeksti() == null
                || lokalisoituTeksti.getTeksti() == null) {
            return "";
        } else {
            return unescapeHtml5(lokalisoituTeksti.getTeksti());
        }
    }

    public static String unescapeHtml5(String string) {
        return StringEscapeUtils.unescapeHtml4((Jsoup.clean(stripNonValidXMLCharacters(string), ValidHtml.WhitelistType.NORMAL_PDF.getWhitelist())));
    }

    public static String stripNonValidXMLCharacters(String in) {
        StringBuilder out = new StringBuilder();
        char current;

        if (in == null || ("".equals(in))) return "";
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i);
            if (current == 0x9
                    || current == 0xA
                    || current == 0xD
                    || current >= 0x20 && current <= 0xD7FF
                    || current >= 0xE000 && current <= 0xFFFD) {
                out.append(current);
            }
        }

        return out.toString();
    }

    public static boolean isTimePass(Dokumentti dokumentti) {
        return isTimePass(dokumentti.getAloitusaika());
    }

    public static boolean isTimePass(DokumenttiDto dokumenttiDto) {
        return isTimePass(dokumenttiDto.getAloitusaika());
    }

    public static boolean isTimePass(Date date) {
        if (date == null) {
            return true;
        }

        Date newDate = DateUtils.addMinutes(date, MAX_TIME_IN_MINUTES);
        return newDate.before(new Date());
    }

    public static ValidationResult validatePdf(byte[] pdf) throws IOException {
        ValidationResult result;
        InputStream is = new ByteArrayInputStream(pdf);
        PreflightParser parser = new PreflightParser(new ByteArrayDataSource(is));

        try {
            parser.parse();

            PreflightDocument document = parser.getPreflightDocument();
            document.validate();

            // Get validation result
            result = document.getResult();
            document.close();

        } catch (SyntaxValidationException e) {
            result = e.getResult();
        }

        return result;
    }

    public static ByteArrayOutputStream printDocument(Document doc) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(out, "UTF-8")));
        } catch (IOException | TransformerException ex) {
            return out;
        }
        return out;
    }

    public static Element newBoldElement(Document doc, String teksti) {
        Element strong = doc.createElement("strong");
        strong.appendChild(doc.createTextNode(teksti));
        return strong;
    }

    public static Element newItalicElement(DokumenttiBase docBase, String teksti) {
        Element emphasis = docBase.getDocument().createElement("em");

        Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(unescapeHtml5(teksti)));
        Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

        emphasis.appendChild(docBase.getDocument().importNode(node, true));
        return emphasis;
    }
}
