package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lops2019LaajaAlainenOsaaminenDto {
    private LokalisoituTekstiDto kuvaus;
    private List<Lops2019LaajaAlainenDto> laajaAlaisetOsaamiset = new ArrayList<>();
}
