package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.ReferenceableDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto implements ReferenceableDto {
    private Long id;
    private Optional<Reference> laajaalainenOsaaminen;
    private Optional<LokalisoituTekstiDto> kuvaus;
}
