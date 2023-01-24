package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.opas.lops2019.oppiaineet.moduuli;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019ModuuliSisaltoDto {
    private LokalisoituTekstiDto kohde;
    private List<LokalisoituTekstiDto> sisallot;
}
