package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.OsaTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class OppiaineBaseUpdateDto extends OppiaineBaseDto {
    private String koodiUri;
    private String koodiArvo;

    private TekstiOsaDto tehtava;
    private TekstiOsaDto tavoitteet;
    private TekstiOsaDto arviointi;

    private LokalisoituTekstiDto pakollinenKurssiKuvaus;
    private LokalisoituTekstiDto syventavaKurssiKuvaus;
    private LokalisoituTekstiDto soveltavaKurssiKuvaus;

    private Boolean partial;

    public TekstiOsaDto getOsa(OsaTyyppi tyyppi) {
        switch (tyyppi) {
            case arviointi: return arviointi;
            case tavoitteet: return tavoitteet;
            case tehtava: return tehtava;
            default: return null;
        }
    }
}
