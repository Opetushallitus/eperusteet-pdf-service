package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TekstiOsaDto {
    private Optional<Long> id;
    private Optional<LokalisoituTekstiDto> otsikko;
    private Optional<LokalisoituTekstiDto> teksti;

    public TekstiOsaDto(Optional<LokalisoituTekstiDto> otsikko, Optional<LokalisoituTekstiDto> teksti) {
        this.otsikko = otsikko;
        this.teksti = teksti;
    }
}
