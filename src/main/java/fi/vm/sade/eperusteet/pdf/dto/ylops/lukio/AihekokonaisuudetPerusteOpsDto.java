package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.AihekokonaisuudetDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AihekokonaisuudetPerusteOpsDto extends PerusteOpsDto<AihekokonaisuudetDto,
        AihekokonaisuudetOpsDto> {
    public AihekokonaisuudetPerusteOpsDto(AihekokonaisuudetOpsDto paikallinen) {
        super(paikallinen);
    }

    public AihekokonaisuudetPerusteOpsDto(AihekokonaisuudetDto perusteen, AihekokonaisuudetOpsDto paikallinen) {
        super(perusteen, paikallinen);
    }
}
