package med.voll.api.domain.medico.exception;

import med.voll.api.system.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;

public class MedicoNotFoundException extends BusinessRuleException {

    public MedicoNotFoundException(Long id) {
        super("Médico com id " + id + " não encontrado", HttpStatus.NOT_FOUND);
    }
}
