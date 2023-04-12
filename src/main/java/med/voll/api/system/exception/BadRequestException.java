package med.voll.api.system.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BusinessRuleException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
