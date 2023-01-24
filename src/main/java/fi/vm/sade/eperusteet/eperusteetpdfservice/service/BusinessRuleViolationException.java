package fi.vm.sade.eperusteet.eperusteetpdfservice.service;

import fi.vm.sade.eperusteet.eperusteetpdfservice.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessRuleViolationException extends ServiceException {

    public BusinessRuleViolationException(String message) {
        super(message);
    }
    public BusinessRuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
