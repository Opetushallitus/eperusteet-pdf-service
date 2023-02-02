package fi.vm.sade.eperusteet.pdf.dto.ylops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(of = {"koodisto", "uri", "versio"})
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KoodiDto {
    Map<String, String> nimi;
    private String arvo;
    private String uri;
    private String koodisto;
    private Long versio;

    static public KoodiDto of(String koodisto, String arvo) {
        KoodiDto result = new KoodiDto();
        result.setUri(koodisto + "_" + arvo);
        result.setKoodisto(koodisto);
        result.setArvo(arvo);
        return result;
    }
}
