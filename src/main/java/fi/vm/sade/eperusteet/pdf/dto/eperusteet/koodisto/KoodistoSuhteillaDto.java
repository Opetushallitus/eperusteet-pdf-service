package fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoDto;
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
public class KoodistoSuhteillaDto extends KoodistoDto {
    private List<KoodistoSuhdeDto> withinCodes;
    private List<KoodistoSuhdeDto> includesCodes;
    private List<KoodistoSuhdeDto> levelsWithCodes;
}
