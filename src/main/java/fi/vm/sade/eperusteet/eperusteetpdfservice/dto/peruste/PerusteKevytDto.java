package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteKevytDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private PerusteTila tila;
    private PerusteTyyppi tyyppi;
    private String koulutustyyppi;
    private boolean esikatseltavissa;
    private Date voimassaoloAlkaa;
    private Date voimassaoloLoppuu;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<SuoritustapaDto> suoritustavat;
}