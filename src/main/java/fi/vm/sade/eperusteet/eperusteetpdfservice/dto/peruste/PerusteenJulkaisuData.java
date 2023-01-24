package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.KoulutusDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteenJulkaisuData {
    private Long id;
    private Map<String, String> nimi;
    private Long voimassaoloAlkaa;
    private Long voimassaoloLoppuu;
    private Long siirtymaPaattyy;
    private String diaarinumero;
    private Set<KoodiDto> osaamisalat;
    private List<TutkintonimikeKoodiDto> tutkintonimikkeet;
    private String koulutustyyppi;
    private Integer laajuus;
    private List<KoulutusDto> koulutukset;
    private Set<SuoritustapaDto> suoritustavat;
    private Date julkaistu;
    private Long luotu;
}