package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KVLiiteDto {
    private Long id;
    private LokalisoituTekstiDto suorittaneenOsaaminen;
    private LokalisoituTekstiDto tyotehtavatJoissaVoiToimia;
    private LokalisoituTekstiDto tutkintotodistuksenAntaja;
    private Reference arvosanaAsteikko;
    private LokalisoituTekstiDto jatkoopintoKelpoisuus;
    private LokalisoituTekstiDto kansainvalisetSopimukset;
    private LokalisoituTekstiDto saadosPerusta;
    private LokalisoituTekstiDto pohjakoulutusvaatimukset;
    private LokalisoituTekstiDto lisatietoja;
    private LokalisoituTekstiDto tutkintotodistuksenSaaminen;
    private LokalisoituTekstiDto tutkinnostaPaattavaViranomainen;
}
