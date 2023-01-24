package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonosa;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Arviointi2020Dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OsaAlueKokonaanDto extends OsaAlueDto {

    @ApiModelProperty("OSAALUE2020-mukainen arviointi")
    private Arviointi2020Dto arviointi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("OSAALUE2020-mukainen pakolliset osaamistavoitteet")
    private Osaamistavoite2020Dto pakollisetOsaamistavoitteet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("OSAALUE2020-mukainen valinnaiset osaamistavoittet")
    private Osaamistavoite2020Dto valinnaisetOsaamistavoitteet;

    @Deprecated
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("Vanhan malliset osaamistavoitteet (OSAALUE2014)")
    private List<OsaamistavoiteLaajaDto> osaamistavoitteet = new ArrayList<>();
}
