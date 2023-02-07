package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommonExternalServiceImpl implements CommonExternalService{

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service:''}")
    private String amosaaServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service:''}")
    private String ylopsServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    private static final String AMOSAA_API = "/api/amosaa/";
    private static final String EPERUSTEET_API = "/api/perusteet/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    HttpEntity httpEntity;

    @Override
    public InputStream getLiitetiedosto(Long id, UUID fileName, DokumenttiTyyppi tyyppi) {
        try {
            ResponseEntity<Resource> exchange = restTemplate.exchange(getLiitetiedostoUrl(tyyppi),
                    HttpMethod.GET,
                    httpEntity,
                    Resource.class,
                    id,
                    fileName);
            return Objects.requireNonNull(exchange.getBody()).getInputStream();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi kuvatyyppi, Kieli kieli, DokumenttiTyyppi dokumenttityyppi, Long ktId) {
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(getDokumenttiKuvaUrl(dokumenttityyppi, ktId),
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    opsId,
                    kuvatyyppi,
                    kieli);
            return response.getBody();
        }  catch (HttpClientErrorException | HttpServerErrorException e) {
            if (e.getStatusCode().is4xxClientError()) {

            } else if (e.getStatusCode().is5xxServerError()) {

            }
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public TermiDto getTermi(Long id, String avain, DokumenttiTyyppi dokumenttityyppi) {
        try {
            ResponseEntity<TermiDto> response = restTemplate.exchange(getTermiUrl(dokumenttityyppi),
                    HttpMethod.GET,
                    httpEntity,
                    TermiDto.class,
                    id,
                    avain);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    private String getTermiUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return eperusteetServiceUrl + EPERUSTEET_API + "{id}/termisto/{avain}";
        } else if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl + AMOSAA_API  + "koulutustoimijat/{id}/termisto/{avain}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/opetussuunnitelmat/{id}/termi/{avain}";
        } else {
            // TODO: poikkeus
            return "";
        }
    }

    private String getLiitetiedostoUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return eperusteetServiceUrl + EPERUSTEET_API + "{id}/liitteet/{fileName}";
        } else if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl + AMOSAA_API + "liitetiedostot/opetussuunnitelmat/{id}/kuvat/{fileName}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/opetussuunnitelmat/{id}/kuvat/{fileName}";
        } else {
            // TODO: poikkeus
            return "";
        }
    }

    private String getDokumenttiKuvaUrl(DokumenttiTyyppi tyyppi, Long ktId) {
        if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl + AMOSAA_API + "koulutustoimijat/" + ktId +"/opetussuunnitelmat/{opsId}/dokumentti/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/dokumentit/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else {
            // TODO: poikkeus
            return "";
        }
    }
}
