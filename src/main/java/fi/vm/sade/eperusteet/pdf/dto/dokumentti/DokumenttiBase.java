package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.utils.CharapterNumberGenerator;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Getter
@Setter
public class DokumenttiBase {
    Document document;
    Element headElement;
    Element bodyElement;
    Kieli kieli;
    GeneratorData generatorData;
    CharapterNumberGenerator generator;
}
