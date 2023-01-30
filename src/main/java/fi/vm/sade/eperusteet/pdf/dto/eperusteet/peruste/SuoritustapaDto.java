package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Suoritustapakoodi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuoritustapaDto {
    private Suoritustapakoodi suoritustapakoodi;
    private LaajuusYksikko laajuusYksikko;
}
