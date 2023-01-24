package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.vst;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenSisaltoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VapaasivistystyoSisaltoDto implements PerusteenSisaltoDto {
    private Long id;
    private PerusteenOsaViiteDto.Laaja sisalto;
    private Integer laajuus;
}
