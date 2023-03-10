package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutustyyppiToteutus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PerusteBaseDto implements Serializable {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private KoulutusTyyppi koulutustyyppi;
    private Set<PerusteKoulutusDto> koulutukset;
    private Set<Kieli> kielet;
    private LokalisoituTekstiDto kuvaus;
    private String diaarinumero;
    private Date voimassaoloAlkaa;
    private Date siirtymaPaattyy;
    private Date voimassaoloLoppuu;
    private Date muokattu;
    private String tila;
    private String tyyppi;
    private KoulutustyyppiToteutus toteutus;
    private Set<String> korvattavatDiaarinumerot;
}
