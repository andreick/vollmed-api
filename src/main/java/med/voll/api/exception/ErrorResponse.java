package med.voll.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp,
        int status, String error, String message, List<SubErrorResponse> errors) {

    public ErrorResponse(HttpStatus status, String message) {
        this(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, null);
    }

    public ErrorResponse(HttpStatus status, String message, List<SubErrorResponse> errors) {
        this(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, errors);
    }
}
