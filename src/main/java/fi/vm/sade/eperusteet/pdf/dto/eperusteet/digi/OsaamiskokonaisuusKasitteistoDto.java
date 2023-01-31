package fi.vm.sade.eperusteet.pdf.dto.eperusteet.digi;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.DigitaalinenOsaaminenTaso;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsaamiskokonaisuusKasitteistoDto {
    private Long id;
    private DigitaalinenOsaaminenTaso taso;
    private LokalisoituTekstiDto kuvaus;
}
