/*
 * Copyright (c) 2013 The Finnish Board of Education - Opetushallitus
 *
 * This program is free software: Licensed under the EUPL, Version 1.1 or - as
 * soon as they will be approved by the European Commission - subsequent versions
 * of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at: http://ec.europa.eu/idabc/eupl
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * European Union Public Licence for more details.
 */
package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.annotation.Identifiable;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.PerusteTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KoulutusDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.MuutosmaaraysDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.utils.PerusteIdentifiable;
import fi.vm.sade.eperusteet.pdf.utils.PerusteUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PerusteBaseDto implements Serializable, PerusteIdentifiable, Identifiable {

    private Long id;
    @JsonIgnore
    private Integer revision;

    @ApiModelProperty("Perusteen sisältöjen viimeisin päivitysaika")
    private PerusteVersionDto globalVersion;

    private LokalisoituTekstiDto nimi;
    private String koulutustyyppi;

    @ApiModelProperty("Perusteen sisäisen rakenteen toteutuksen ilmaiseva sisältö. Esimerkiksi vanhan ja uuden lukion toteutus (LOPS vs LOPS2019)")
    private KoulutustyyppiToteutus toteutus;
    private Set<KoulutusDto> koulutukset;
    private Set<Kieli> kielet;
    private LokalisoituTekstiDto kuvaus;
    private MaarayskirjeDto maarayskirje;
    private List<MuutosmaaraysDto> muutosmaaraykset = new ArrayList<>();

    private String diaarinumero;
    private Date voimassaoloAlkaa;

    @ApiModelProperty("Voimassaolon loppumisen jälkeinen perusteen päättymispäivämäärä.")
    private Date siirtymaPaattyy;
    private Date voimassaoloLoppuu;

    @ApiModelProperty("Perusteen määräyksen päätöspäivämäärä")
    private Date paatospvm;
    private Optional<Date> viimeisinJulkaisuAika;

    private Date luotu;
    @ApiModelProperty(hidden = true)
    private Date muokattu;

    @ApiModelProperty("Perusteen sisäinen tila. Ei enää merkityksellinen julkaisujen käytönoton jälkeen")
    private PerusteTila tila;
    private PerusteTyyppi tyyppi;

    @ApiModelProperty("EU- ja ETA-maiden koulutusvientikokeiluun tarkoitettu peruste")
    private Boolean koulutusvienti;

    @ApiModelProperty("Perusteen vanhemmat määräykset. Eivät välttämättä ole toteutettu ePerusteisiin")
    private Set<String> korvattavatDiaarinumerot;

    @ApiModelProperty("Perusteeseen liittyvät osaamisalakoodit")
    private Set<KoodiDto> osaamisalat;

    // Tuodaan kvliitteestä
    @ApiModelProperty("kv-liitteen lisätieto")
    private LokalisoituTekstiDto tyotehtavatJoissaVoiToimia;

    @ApiModelProperty("kv-liitteen lisätieto")
    private LokalisoituTekstiDto suorittaneenOsaaminen;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<TutkintonimikeKoodiDto> tutkintonimikkeet;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("Perusteet joihin opas liittyy")
    private Set<PerusteKevytDto> oppaanPerusteet;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("Koulutustyypit joihin opas liittyy")
    private Set<KoulutusTyyppi> oppaanKoulutustyypit;

    @ApiModelProperty("Perusteprosessin päivämäärät")
    private Set<PerusteAikatauluDto> perusteenAikataulut;

    @Override
    public KoulutustyyppiToteutus getToteutus() {
        return PerusteUtils.getToteutus(this.toteutus, this.koulutustyyppi, this.tyyppi);
    }
}
