package med.voll.api.system.exception.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationErrorResponse extends ErrorResponse {

    private List<Error> errors;

    public ValidationErrorResponse(String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    @NoArgsConstructor
    @Getter
    public static class Error {

        private String field;
        private Object rejectedValue;
        private String message;

        public Error(FieldError error) {
            this.field = error.getField();
            this.rejectedValue = error.getRejectedValue();
            this.message = error.getDefaultMessage();
        }
    }
}
