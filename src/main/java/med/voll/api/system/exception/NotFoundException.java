package med.voll.api.system.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessRuleException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
