package fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019OppiaineTavoitteetDto {
    private LokalisoituTekstiDto kuvaus;
    private List<Lops2019OppiaineTavoitealueDto> tavoitealueet;
}
