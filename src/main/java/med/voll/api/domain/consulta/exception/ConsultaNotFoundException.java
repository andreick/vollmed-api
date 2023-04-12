package med.voll.api.domain.consulta.exception;

import med.voll.api.system.exception.NotFoundException;

public class ConsultaNotFoundException extends NotFoundException {

    public ConsultaNotFoundException(Long id) {
        super("Consulta com id " + id + " não encontrada");
    }
}
