package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.utils.domain.utils.Tila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TekstiKappaleDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    private String muokkaaja;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String muokkaajanNimi;

    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
    private UUID tunniste;
    private Boolean pakollinen;
    private Boolean valmis;

    public TekstiKappaleDto(LokalisoituTekstiDto nimi, LokalisoituTekstiDto teksti, Tila tila) {
        this.nimi = nimi;
        this.teksti = teksti;
    }
}
