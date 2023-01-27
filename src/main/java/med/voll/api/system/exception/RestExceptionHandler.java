package med.voll.api.system.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
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

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var error = new ErrorResponse(status, ex.getMessage());
        return new ResponseEntity<>(error, status);
    }
}
