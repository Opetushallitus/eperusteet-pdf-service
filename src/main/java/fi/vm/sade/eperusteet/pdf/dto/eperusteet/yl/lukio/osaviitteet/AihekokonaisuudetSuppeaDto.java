package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio.osaviitteet;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("aihekokonaisuudet")
public class AihekokonaisuudetSuppeaDto extends PerusteenOsaDto.Suppea {
    private LokalisoituTekstiDto otsikko;
}
