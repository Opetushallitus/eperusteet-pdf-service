package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Suoritustapakoodi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KVLiiteJulkinenDto extends KVLiiteDto {
    private LokalisoituTekstiDto nimi;
    private String koulutustyyppi;
    private LokalisoituTekstiDto kuvaus;
    private MaarayskirjeDto maarayskirje;
    private String diaarinumero;
    private Date voimassaoloAlkaa;
    private List<KVLiiteTasoDto> tasot = new ArrayList<>();
    private Map<Suoritustapakoodi, LokalisoituTekstiDto> muodostumisenKuvaus;
    private Boolean periytynyt;
}
