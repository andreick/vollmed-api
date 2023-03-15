package med.voll.api.system.exception.handler;

import med.voll.api.system.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(BusinessRuleException ex) {
        var status = ex.getStatus();
        var error = new ErrorResponse(status, ex.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleFieldErrors(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var errors = ex.getFieldErrors().stream().map(ValidationErrorResponse.Error::new).toList();
        var error = new ValidationErrorResponse(status, "Campos inválidos", errors);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(Exception ex) {
        var status = HttpStatus.UNAUTHORIZED;
        var error = new ErrorResponse(status, ex.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex) {
        ex.printStackTrace();
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var error = new ErrorResponse(status, ex.getMessage());
        return new ResponseEntity<>(error, status);
    }
}
