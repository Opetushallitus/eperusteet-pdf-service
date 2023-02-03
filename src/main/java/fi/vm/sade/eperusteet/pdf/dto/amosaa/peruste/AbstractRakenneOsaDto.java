package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractRakenneOsaDto {
    private LokalisoituTekstiDto kuvaus;
    private KoodiDto vieras;
    private UUID tunniste;
    private Boolean pakollinen;

    public final void foreach(final Visitor visitor) {
        foreach(visitor, 0);
    }

    protected abstract void foreach(final Visitor visitor, final int currentDepth);

    public interface Visitor {
        void visit(final AbstractRakenneOsaDto dto, final int depth);
    }

}