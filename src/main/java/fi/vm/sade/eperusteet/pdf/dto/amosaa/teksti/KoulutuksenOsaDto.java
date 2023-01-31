package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KoulutusOsanKoulutustyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KoulutusOsanTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoulutuksenOsaDto extends KoulutuksenOsaKevytDto {

    private Integer laajuusMinimi;
    private Integer laajuusMaksimi;
    private KoulutusOsanKoulutustyyppi koulutusOsanKoulutustyyppi;
    private KoulutusOsanTyyppi koulutusOsanTyyppi;
    private LokalisoituTekstiDto kuvaus;
    private List<LokalisoituTekstiDto> tavoitteet;
    private LokalisoituTekstiDto keskeinenSisalto;
    private LokalisoituTekstiDto laajaAlaisenOsaamisenKuvaus;
    private LokalisoituTekstiDto arvioinninKuvaus;
    private KoulutuksenosanPaikallinenTarkennusDto paikallinenTarkennus;

}
