package med.voll.api.domain.medico.exception;

import med.voll.api.system.exception.ConflictException;

public class MedicoUnavailableException extends ConflictException {

    public MedicoUnavailableException() {
        super("Nenhum médico disponível");
    }

    public MedicoUnavailableException(Long id) {
        super("Médico com id " + id + " indisponível");
    }
}
