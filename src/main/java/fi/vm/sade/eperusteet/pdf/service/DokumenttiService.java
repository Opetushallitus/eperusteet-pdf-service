package fi.vm.sade.eperusteet.pdf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;

public interface DokumenttiService {

    void generateForEperusteet(Long dokumenttiId, Kieli kieli, GeneratorVersion versio, String perusteJson) throws JsonProcessingException, DokumenttiException;

    void generateForAmosaa(Long dokumenttiId, Kieli kieli, Long ktId, String opsJson) throws JsonProcessingException, DokumenttiException;

    void generateForYlops(Long dokumenttiId, Kieli kieli, String opsJson) throws JsonProcessingException, DokumenttiException;
}
