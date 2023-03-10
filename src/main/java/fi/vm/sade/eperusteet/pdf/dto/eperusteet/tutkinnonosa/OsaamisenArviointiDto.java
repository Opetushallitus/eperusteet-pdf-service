package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsaamisenArviointiDto {
    private Long id;
    private LokalisoituTekstiDto kohde;
    private LokalisoituTekstiDto selite;
    private List<LokalisoituTekstiDto> tavoitteet;
}
