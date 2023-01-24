package fi.vm.sade.eperusteet.eperusteetpdfservice.dto;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.MuokkausTapahtuma;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.NavigationType;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerusteenMuokkaustietoDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private MuokkausTapahtuma tapahtuma;
    private Long perusteId;
    private Long kohdeId;
    private NavigationType kohde;
    private Date luotu;
    private String muokkaaja;
    private String lisatieto;
    private boolean poistettu;
}
