package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class JulkaisuDto extends JulkaisuBaseDto {
    private JulkaisuDataDto data;
}
