package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.opas.lops2019.oppiaineet.moduuli;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019ModuuliTavoiteDto {
    private LokalisoituTekstiDto kohde;
    private List<LokalisoituTekstiDto> tavoitteet;
}
