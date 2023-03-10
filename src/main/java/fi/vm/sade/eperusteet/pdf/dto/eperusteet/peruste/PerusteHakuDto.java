package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.CombinedDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerusteHakuDto extends PerusteDto {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<KoodiDto> tutkintonimikeKoodit;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CombinedDto<TutkintonimikeKoodiDto, HashMap<String, KoodistoKoodiDto>>> tutkintonimikkeetKoodisto = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PerusteInfoDto> korvaavatPerusteet = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PerusteInfoDto> korvattavatPerusteet = new ArrayList<>();
}
