package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.oppiaineet.moduuli;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019ModuuliDto extends Lops2019ModuuliBaseDto {
    private LokalisoituTekstiDto kuvaus;
    private BigDecimal laajuus;
    private Lops2019ModuuliTavoiteDto tavoitteet;
    private List<Lops2019ModuuliSisaltoDto> sisallot = new ArrayList<>();
    private Reference oppiaine;
}
