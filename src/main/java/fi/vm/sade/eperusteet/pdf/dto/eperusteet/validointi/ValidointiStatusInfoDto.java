package fi.vm.sade.eperusteet.pdf.dto.eperusteet.validointi;

import fi.vm.sade.eperusteet.pdf.domain.TekstiPalanen;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.enums.Suoritustapakoodi;
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