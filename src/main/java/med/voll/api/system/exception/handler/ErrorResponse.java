package med.voll.api.system.exception.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class ErrorResponse {

    private Date timestamp = new Date();
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
