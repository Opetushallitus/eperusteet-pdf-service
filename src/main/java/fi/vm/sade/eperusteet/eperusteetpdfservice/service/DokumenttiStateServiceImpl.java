package fi.vm.sade.eperusteet.eperusteetpdfservice.service;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Dokumentti;
import fi.vm.sade.eperusteet.eperusteetpdfservice.repository.DokumenttiRepository;
import fi.vm.sade.eperusteet.eperusteetpdfservice.service.util.IgnorePerusteUpdateCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DokumenttiStateServiceImpl implements DokumenttiStateService {

    @Autowired
    private DokumenttiRepository dokumenttiRepository;

    @IgnorePerusteUpdateCheck
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Dokumentti save(Dokumentti dokumentti) {
        return dokumenttiRepository.save(dokumentti);
    }
}