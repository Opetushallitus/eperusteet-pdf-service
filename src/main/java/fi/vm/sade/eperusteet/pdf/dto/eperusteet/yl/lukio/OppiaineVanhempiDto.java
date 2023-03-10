package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.Lokalisoitava;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineVanhempiDto implements Serializable, Lokalisoitava {
    private Long oppiaineId;
    private LokalisoituTekstiDto oppiaineNimi;
    private OppiaineVanhempiDto vanhempi;

    public OppiaineVanhempiDto(Long oppiaineId, Long oppiaineNimiId, OppiaineVanhempiDto vanhempi) {
        this.oppiaineId = oppiaineId;
        this.oppiaineNimi = LokalisoituTekstiDto.localizeLaterById(oppiaineNimiId);
        this.vanhempi = vanhempi;
    }

    @Override
    public Stream<LokalisoituTekstiDto> lokalisoitavatTekstit() {
        return Lokalisoitava.of(oppiaineNimi).and(vanhempi).lokalisoitavatTekstit();
    }
}
