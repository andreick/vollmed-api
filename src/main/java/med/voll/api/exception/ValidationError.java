package med.voll.api.exception;

import org.springframework.validation.FieldError;

public record ValidationError(String field, Object rejectedValue, String message) implements SubErrorResponse {

    public ValidationError(FieldError error) {
        this(error.getField(), error.getRejectedValue(), error.getDefaultMessage());
    }
}
