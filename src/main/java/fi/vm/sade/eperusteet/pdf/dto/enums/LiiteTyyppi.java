package fi.vm.sade.eperusteet.pdf.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LiiteTyyppi {

    TUNTEMATON("tuntematon"),
    KUVA("kuva"),
    DOKUMENTTI("dokumentti"),
    MAARAYSKIRJE("maarayskirje"),
    MUUTOSMAARAYS("muutosmaarays"),
    KOULUTUSVIENNINOHJE("KOULUTUSVIENNINOHJE");

    private final String tyyppi;

    private LiiteTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }

    @JsonCreator
    public static LiiteTyyppi of(String tyyppi) {
        for (LiiteTyyppi s : values()) {
            if (s.tyyppi.equalsIgnoreCase(tyyppi)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tyyppi + " ei ole kelvollinen LiiteTyyppi");
    }
}
