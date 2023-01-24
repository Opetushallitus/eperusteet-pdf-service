package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Vuosiluokka;
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
public class VuosiluokkaKokonaisuusDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private Set<Vuosiluokka> vuosiluokat;
    private Optional<LokalisoituTekstiDto> nimi;
    private Optional<TekstiOsaDto> siirtymaEdellisesta;
    private Optional<TekstiOsaDto> tehtava;
    private Optional<TekstiOsaDto> siirtymaSeuraavaan;
    private Optional<TekstiOsaDto> laajaalainenOsaaminen;
    private Set<VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto> laajaalaisetOsaamiset;
    private Optional<TekstiOsaDto> paikallisestiPaatettavatAsiat;
}
