package fi.vm.sade.eperusteet.eperusteetpdfservice.dto;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutusDto {
    private LokalisoituTekstiDto nimi;
    private String koulutuskoodiArvo;
    private String koulutuskoodiUri;
    private String koulutusalakoodi;
    private String opintoalakoodi;
}
