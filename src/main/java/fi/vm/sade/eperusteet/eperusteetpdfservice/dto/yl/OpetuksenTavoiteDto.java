package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.ReferenceableDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpetuksenTavoiteDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private Optional<LokalisoituTekstiDto> tavoite;
    private Set<Reference> sisaltoalueet;
    private Set<Reference> laajattavoitteet;
    private Set<Reference> kohdealueet;
    private Set<TavoitteenArviointiDto> arvioinninkohteet;
    private Optional<LokalisoituTekstiDto> arvioinninOtsikko;
    private Optional<LokalisoituTekstiDto> arvioinninKuvaus;
    private Optional<LokalisoituTekstiDto> arvioinninOsaamisenKuvaus;
    private Optional<LokalisoituTekstiDto> tavoitteistaJohdetutOppimisenTavoitteet;
    private Optional<LokalisoituTekstiDto> vapaaTeksti;
}