package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.TutkinnonosaTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutkinnonosaBaseDto {
    private Long id;
    private TutkinnonosaTyyppi tyyppi;
    private String koodi;
    private Date muokattu;
}
