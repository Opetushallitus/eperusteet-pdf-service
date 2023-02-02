package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste;

import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsiopetuksenPerusteenSisaltoDto {
    private TekstiKappaleViiteDto sisalto;
}
