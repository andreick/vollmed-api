package med.voll.api.domain.paciente.exception;

import med.voll.api.system.exception.NotFoundException;

public class PacienteNotFoundException extends NotFoundException {

    public PacienteNotFoundException(Long id) {
        super("Paciente com id " + id + " não encontrado");
    }
}
