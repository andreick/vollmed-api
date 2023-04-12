package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.exception.MedicoNotFoundException;
import med.voll.api.domain.medico.exception.MedicoUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoValidation implements ConsultaScheduleValidation {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(ConsultaScheduleDto dto) {
        Long id = dto.idMedico();
        if (id == null) {
            return;
        }
        if (!medicoRepository.existsByIdAndAtivoTrue(id)) {
            throw new MedicoNotFoundException(id);
        }
        var start = dto.data().minusHours(1);
        var end = dto.data().plusHours(1);
        if (consultaRepository.existsByMedicoIdAndDataGreaterThanAndDataLessThan(id, start, end)) {
            throw new MedicoUnavailableException(id);
        }
    }
}
