package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti.PerusteprojektiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerusteHakuInternalDto extends PerusteHakuDto {
    private PerusteprojektiDto perusteprojekti;
}
