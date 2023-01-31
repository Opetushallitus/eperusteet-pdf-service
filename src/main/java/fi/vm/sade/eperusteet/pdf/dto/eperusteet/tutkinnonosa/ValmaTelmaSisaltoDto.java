package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("valmaTelmaSisalto")
public class ValmaTelmaSisaltoDto {
    private Long id;
    private OsaamisenArviointiDto osaamisenarviointi;
    private LokalisoituTekstiDto osaamisenarviointiTekstina;
    private List<OsaamisenTavoiteDto> osaamistavoite;
}
