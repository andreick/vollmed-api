package med.voll.api.domain.consulta.validation;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.exception.PacienteNotFoundException;
import med.voll.api.domain.paciente.exception.PacienteUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class PacienteValidation implements ConsultaScheduleValidation {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(ConsultaScheduleDto dto) {
        Long id = dto.idPaciente();
        if (!pacienteRepository.existsByIdAndAtivoTrue(id)) {
            throw new PacienteNotFoundException(id);
        }
        var start = dto.data().with(LocalTime.MIN);
        var end = dto.data().with(LocalTime.MAX);
        if (consultaRepository.existsByPacienteIdAndDataBetween(id, start, end)) {
            throw new PacienteUnavailableException("Paciente já possui uma consulta marcada neste dia");
        }
    }
}
