package med.voll.api.domain.medico.exception;

import med.voll.api.system.exception.NotFoundException;

public class MedicoNotFoundException extends NotFoundException {

    public MedicoNotFoundException(Long id) {
        super("Médico com id " + id + " não encontrado");
    }
}
