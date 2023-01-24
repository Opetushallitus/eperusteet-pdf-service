package fi.vm.sade.eperusteet.pdf.service.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
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
    private DokumenttiTyyppi tyyppi;
    private DokumenttiTila tila = DokumenttiTila.EI_OLE;
    private Kieli kieli;
    private Integer revision;
    private Date aloitusaika;
    private Date valmistumisaika;
    private byte[] data;
}
