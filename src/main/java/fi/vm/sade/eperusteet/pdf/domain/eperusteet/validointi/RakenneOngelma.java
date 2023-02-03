package fi.vm.sade.eperusteet.pdf.domain.eperusteet.validointi;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.TekstiPalanen;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RakenneOngelma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    public String ongelma;

    @Getter
    @Setter
    @OneToOne
    public TekstiPalanen ryhma;
}