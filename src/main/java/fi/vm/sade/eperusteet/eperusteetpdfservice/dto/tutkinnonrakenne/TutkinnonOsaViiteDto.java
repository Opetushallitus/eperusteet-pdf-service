package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.ReferenceableDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Sortable;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonosa.TutkinnonOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TutkinnonOsaViiteDto implements ReferenceableDto, Sortable {

    private Long id;
    private BigDecimal laajuus;
    private BigDecimal laajuusMaksimi; // TODO: Ainoastaan valmatelmalla
    private Integer jarjestys;
    @JsonProperty("_tutkinnonOsa")
    private Reference tutkinnonOsa;
    @JsonProperty("tutkinnonOsa")
    private TutkinnonOsaDto tutkinnonOsaDto;

    private Date muokattu;
    private LokalisoituTekstiDto nimi;
    private TutkinnonOsaTyyppi tyyppi;

    public TutkinnonOsaViiteDto(BigDecimal laajuus, Integer jarjestys, LokalisoituTekstiDto nimi, TutkinnonOsaTyyppi tyyppi) {
        this.laajuus = laajuus;
        this.jarjestys = jarjestys;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
    }
}
