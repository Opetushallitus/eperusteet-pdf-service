package fi.vm.sade.eperusteet.pdf.dto.enums;

public enum PoistettuTyyppi {
    TEKSTIKAPPALEVIITE("tekstikappaleviite");

    private final String tila;

    private PoistettuTyyppi(String tila) {
        this.tila = tila;
    }

    @Override
    public String toString() {
        return tila;
    }
}
