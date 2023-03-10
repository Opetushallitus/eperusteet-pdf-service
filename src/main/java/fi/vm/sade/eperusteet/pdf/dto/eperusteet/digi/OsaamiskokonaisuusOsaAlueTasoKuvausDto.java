package fi.vm.sade.eperusteet.pdf.dto.eperusteet.digi;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DigitaalinenOsaaminenTaso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OsaamiskokonaisuusOsaAlueTasoKuvausDto {
    private Long id;
    private DigitaalinenOsaaminenTaso taso;
    private List<LokalisoituTekstiDto> kuvaukset = new ArrayList<>();
    private List<LokalisoituTekstiDto> edistynytOsaaminenKuvaukset = new ArrayList<>();
}
