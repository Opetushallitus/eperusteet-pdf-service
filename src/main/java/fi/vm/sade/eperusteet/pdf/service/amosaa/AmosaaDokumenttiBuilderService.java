package fi.vm.sade.eperusteet.pdf.service.amosaa;

import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface AmosaaDokumenttiBuilderService {

    Document generateXML(Dokumentti dokumentti, Long ktId, OpetussuunnitelmaKaikkiDto ops)
            throws ParserConfigurationException, IOException, SAXException, TransformerException;
}
