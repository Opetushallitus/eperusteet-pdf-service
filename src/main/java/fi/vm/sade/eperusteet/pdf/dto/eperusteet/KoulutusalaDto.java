package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutusalaDto {
    private String koodi;
    private LokalisoituTekstiDto nimi;
    private List<OpintoalaDto> opintoalat;
}