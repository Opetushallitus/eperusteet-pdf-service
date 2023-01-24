package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonosa;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.ammattitaitovaatimukset.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.arviointi.ArviointiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.NavigationType;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteKevytDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("tutkinnonosa")
@Builder
public class TutkinnonOsaDto extends PerusteenOsaDto.Laaja {
    private LokalisoituTekstiDto tavoitteet;
    private ArviointiDto arviointi;
    private List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimuksetLista;
    private LokalisoituTekstiDto ammattitaitovaatimukset;
    private LokalisoituTekstiDto ammattitaidonOsoittamistavat;
    private LokalisoituTekstiDto kuvaus;
    private KoodiDto koodi;
    private String koodiUri;
    private String koodiArvo;
    private List<OsaAlueDto> osaAlueet;
    private List<KevytTekstiKappaleDto> vapaatTekstit;
    private TutkinnonOsaTyyppi tyyppi;
    private ValmaTelmaSisaltoDto valmaTelmaSisalto;
    private Ammattitaitovaatimukset2019Dto ammattitaitovaatimukset2019;
    private Reference geneerinenArviointiasteikko;
    private PerusteKevytDto alkuperainenPeruste;

    public TutkinnonOsaDto (LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        super(nimi, tila, tunniste);
    }

    @Override
    public String getOsanTyyppi() {
        return "tutkinnonosa";
    }

    public String getKoodiUri() {
        KoodiDto koodi = this.getKoodi();
        if (koodi != null) {
            return koodi.getUri();
        } else {
            return koodiUri;
        }
    }

    public String getKoodiArvo() {
        KoodiDto koodi = this.getKoodi();
        if (koodi != null) {
            return koodi.getArvo();
        } else {
            return koodiArvo;
        }
    }

    @Override
    public LokalisoituTekstiDto getNimi() {
        if (koodi != null && koodi.getNimi() != null && !CollectionUtils.isEmpty(koodi.getNimi().getTekstit())) {
            Map<String, String> kielet = new HashMap<>();
            kielet.computeIfAbsent("fi", val -> koodi.getNimi().getTekstit().getOrDefault(Kieli.FI, super.getNimi().get(Kieli.FI)));
            kielet.computeIfAbsent("sv", val -> koodi.getNimi().getTekstit().getOrDefault(Kieli.SV, super.getNimi().get(Kieli.SV)));
            kielet.computeIfAbsent("en", val -> koodi.getNimi().getTekstit().getOrDefault(Kieli.EN, super.getNimi().get(Kieli.EN)));
            return new LokalisoituTekstiDto(kielet);
        } else {
            return super.getNimi();
        }
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.tutkinnonosa;
    }
}
