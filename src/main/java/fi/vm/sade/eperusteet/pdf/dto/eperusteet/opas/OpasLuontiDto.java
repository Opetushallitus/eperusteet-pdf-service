package fi.vm.sade.eperusteet.pdf.dto.eperusteet.opas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKevytDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class OpasLuontiDto extends OpasDto {

    private Long pohjaId;
    private Set<PerusteKevytDto> oppaanPerusteet;
    private Set<KoulutusTyyppi> oppaanKoulutustyypit;
    private LokalisoituTekstiDto lokalisoituNimi;

}
