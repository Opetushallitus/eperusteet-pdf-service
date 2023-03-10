package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface PdfService {
    byte[] xhtml2pdf(Document document, DokumenttiMetaDto meta, DokumenttiTyyppi tyypi) throws IOException, TransformerException, SAXException, DokumenttiException;
}
