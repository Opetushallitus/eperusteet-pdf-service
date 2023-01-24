package fi.vm.sade.eperusteet.eperusteetpdfservice.service.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DokumenttiDto {
    private Long id;
    private Long sisaltoId;
    private String tyyppi;
    private DokumenttiTila tila = DokumenttiTila.EI_OLE;
    private Kieli kieli;
    private Integer revision;
    private Date aloitusaika;
    private Date valmistumisaika;
    private byte[] data;
}