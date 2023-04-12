package med.voll.api.domain.paciente.exception;

import med.voll.api.system.exception.ConflictException;

public class PacienteUnavailableException extends ConflictException {

    public PacienteUnavailableException(String message) {
        super(message);
    }

    public PacienteUnavailableException(Long id) {
        super("Paciente com id " + id + " indisponível");
    }
}
