package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LocalizedMessagesService {
    @PreAuthorize("permitAll()")
    String translate(String key, Kieli kieli);
}