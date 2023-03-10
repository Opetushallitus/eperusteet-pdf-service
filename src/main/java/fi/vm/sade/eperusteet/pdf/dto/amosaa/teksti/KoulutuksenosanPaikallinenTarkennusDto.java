package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutuksenosanPaikallinenTarkennusDto {
    private Long id;
    private LokalisoituTekstiDto tavoitteetKuvaus;
    private List<LokalisoituTekstiDto> tavoitteet = new ArrayList<>();
    private List<KoulutuksenosaLaajaalainenOsaaminenDto> laajaalaisetosaamiset = new ArrayList<>();
    private LokalisoituTekstiDto keskeinenSisalto;
    private LokalisoituTekstiDto arvioinninKuvaus;
    private List<KoulutuksenJarjestajaDto> koulutuksenJarjestajat = new ArrayList<>();
}
