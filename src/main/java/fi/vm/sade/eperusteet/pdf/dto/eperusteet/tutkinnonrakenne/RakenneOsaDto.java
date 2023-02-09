package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

import fi.vm.sade.eperusteet.pdf.dto.common.AbstractRakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RakenneOsaDto extends AbstractRakenneOsaDto {
    private String erikoisuus;
    private Reference tutkinnonOsaViite;

    @Override
    public String validationIdentifier() {
        return tutkinnonOsaViite != null
                ? tutkinnonOsaViite.getId()
                : "";
    }

    public static RakenneOsaDto of(Reference tov) {
        RakenneOsaDto result = new RakenneOsaDto();
        result.setTutkinnonOsaViite(tov);
        return result;
    }

    public static RakenneOsaDto of(ReferenceableDto refDto) {
        RakenneOsaDto result = new RakenneOsaDto();
        result.setTutkinnonOsaViite(new Reference(refDto.getId()));
        return result;
    }

    @Override
    protected void foreach(final Visitor visitor, final int depth) {
        visitor.visit(this, depth);
    }
}
