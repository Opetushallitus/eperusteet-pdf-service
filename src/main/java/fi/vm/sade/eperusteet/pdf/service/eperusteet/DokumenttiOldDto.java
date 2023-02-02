package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.DokumenttiVirhe;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Suoritustapakoodi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//TODO: luokka vain devausta varten, kunnes kälillä on uusi dto saatavilla
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DokumenttiOldDto {
    private Long id;
    private Long perusteId;
    private String luoja;
    private Kieli kieli;
    private Date aloitusaika;
    private Date valmistumisaika;
    private DokumenttiTila tila = DokumenttiTila.EI_OLE;
    private DokumenttiVirhe virhekoodi = DokumenttiVirhe.EI_VIRHETTA;
    private Suoritustapakoodi suoritustapakoodi;
    private GeneratorVersion generatorVersion;
}
