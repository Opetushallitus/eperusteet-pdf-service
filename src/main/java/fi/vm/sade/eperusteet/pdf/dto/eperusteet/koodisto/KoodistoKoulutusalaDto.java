package fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoMetadataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoKoulutusalaDto {
    private String koodiUri;
    private String versio;
    private KoodistoMetadataDto[] metadata;
}
