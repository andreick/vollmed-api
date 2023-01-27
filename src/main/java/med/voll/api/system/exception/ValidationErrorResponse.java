package med.voll.api.system.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class ValidationErrorResponse extends ErrorResponse {

    private final List<Error> errors;

    public ValidationErrorResponse(HttpStatus status, String message, List<Error> errors) {
        super(status, message);
        this.errors = errors;
    }

    @Getter
    public static class Error {

        private final String field;
        private final Object rejectedValue;
        private final String message;

        public Error(FieldError error) {
            this.field = error.getField();
            this.rejectedValue = error.getRejectedValue();
            this.message = error.getDefaultMessage();
        }
    }
}
