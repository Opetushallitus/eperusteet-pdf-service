package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Vuosiluokka;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OppiaineenVuosiluokkaKevytDto implements ReferenceableDto {
    private Long id;
    private Vuosiluokka vuosiluokka;
}
