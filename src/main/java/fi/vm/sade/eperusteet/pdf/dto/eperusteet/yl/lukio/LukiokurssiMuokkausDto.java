package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import com.sun.istack.NotNull;
import fi.vm.sade.eperusteet.pdf.domain.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.IdHolder;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LukiokurssiMuokkausDto implements Serializable, IdHolder {
    @NotNull
    private Long id;
    @NotNull
    private LukiokurssiTyyppi tyyppi;
    @NotNull
    private LokalisoituTekstiDto nimi;
    private String koodiArvo;
    private String koodiUri;
    private LokalisoituTekstiDto lokalisoituKoodi;
    private LokalisoituTekstiDto kuvaus;
    private TekstiOsaDto tavoitteet;
    private TekstiOsaDto keskeinenSisalto;
    private TekstiOsaDto tavoitteetJaKeskeinenSisalto;
    private TekstiOsaDto arviointi;
    private String kommentti;
}