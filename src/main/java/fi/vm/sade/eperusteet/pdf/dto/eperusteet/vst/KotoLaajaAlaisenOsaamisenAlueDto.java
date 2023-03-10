package fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KotoLaajaAlaisenOsaamisenAlueDto {
    private KoodiDto koodi;
    private LokalisoituTekstiDto kuvaus;
}
