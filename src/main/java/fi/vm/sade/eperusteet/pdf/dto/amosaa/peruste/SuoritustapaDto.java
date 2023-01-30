package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuoritustapaDto {
    private Suoritustapakoodi suoritustapakoodi;
    private LaajuusYksikko laajuusYksikko;

}
