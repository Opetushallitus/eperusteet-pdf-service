package fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KoodistoPageDto {
    private int sivu = 0;
    private int sivukoko = 25;
    private String kieli = Kieli.FI.toString();
    private boolean onlyValidKoodis;
}
