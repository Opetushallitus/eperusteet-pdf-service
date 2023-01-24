package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonosa;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteenOsaTunniste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutkinnonOsaTilaDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PerusteenOsaTunniste tunniste;
    private Boolean valmis;
    private Boolean kaannettava;
}
