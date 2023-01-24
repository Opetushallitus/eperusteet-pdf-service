package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.validointi;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.TekstiPalanen;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Suoritustapakoodi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidointiStatusInfoDto {
    private String viesti;
    private RakenneValidointiDto validointi;
    private List<TekstiPalanen> nimet = new ArrayList<>();
    private Suoritustapakoodi suoritustapa;
    private Set<Kieli> kieli;
}