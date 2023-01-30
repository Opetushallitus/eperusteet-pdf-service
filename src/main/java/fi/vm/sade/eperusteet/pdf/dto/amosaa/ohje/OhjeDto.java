package fi.vm.sade.eperusteet.pdf.dto.amosaa.ohje;

import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.KoulutustoimijaBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OhjeDto {
    private Long id;
    private String kysymys;
    private String vastaus;
    private LokalisoituTekstiDto lokalisoituKysymys;
    private LokalisoituTekstiDto lokalisoituVastaus;
    private Date muokattu;
    private Set<KoulutustoimijaBaseDto> koulutustoimijat;
    private KoulutustyyppiToteutus toteutus;
}
