package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.CachedPerusteKevytDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Käytetään kaikkien sisältösolmujen pohjana nimen ja tyyppitiedot viemiseen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SisaltoViiteExportBaseDto {
    private Long id;
    private SisaltoTyyppi tyyppi;
    private CachedPerusteKevytDto peruste;
}
