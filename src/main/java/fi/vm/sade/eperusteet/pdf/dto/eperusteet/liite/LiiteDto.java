package fi.vm.sade.eperusteet.pdf.dto.eperusteet.liite;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.LiiteTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LiiteDto extends LiiteBaseDto {
    private LiiteTyyppi tyyppi;
    private Date luotu;
    private String mime;
    private String lisatieto;
}
