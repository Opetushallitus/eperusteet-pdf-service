package fi.vm.sade.eperusteet.pdf.service.amosaa;

import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface AmosaaPdfService {

    byte[] xhtml2pdf(Document document, DokumenttiMetaDto meta) throws IOException, TransformerException, SAXException;
}
