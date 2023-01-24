package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.opas.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019TehtavaDto {
    private LokalisoituTekstiDto kuvaus;
}
