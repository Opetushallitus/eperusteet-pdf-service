package fi.vm.sade.eperusteet.pdf.service.amosaa;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.service.util.CharapterNumberGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmosaaDokumenttiBase {
    Document document;
    Element headElement;
    Element bodyElement;
    OpetussuunnitelmaDto opetussuunnitelma;
    PerusteKaikkiDto peruste;
    CharapterNumberGenerator generator;
    Kieli kieli;
    Dokumentti dokumentti;
}
