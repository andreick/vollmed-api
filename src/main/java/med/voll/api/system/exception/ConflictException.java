package med.voll.api.system.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends BusinessRuleException {

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
