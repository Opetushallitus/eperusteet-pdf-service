package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.ProjektiTila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteprojektinPerusteenosaDto implements Serializable  {
    private Long id;
    private String nimi;
    private ProjektiTila tila;
    private String perusteendiaarinumero;
    private String diaarinumero;
    private String koulutustyyppi;
    private PerusteTyyppi tyyppi;
    private Date luotu;
}
