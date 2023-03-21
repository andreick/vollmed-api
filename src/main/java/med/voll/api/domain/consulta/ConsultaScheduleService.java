package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.dto.ConsultaCancelDto;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.consulta.validation.ConsultaScheduleValidation;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.exception.MedicoUnavailableException;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaScheduleService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ConsultaScheduleValidation> validations;

    public Consulta schedule(ConsultaScheduleDto dto) {
        validations.forEach(validation -> validation.validate(dto));
        var medico = getAvailableMedico(dto);
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        return consultaRepository.save(new Consulta(null, paciente, medico, dto.data()));
    }

    public Medico getAvailableMedico(ConsultaScheduleDto dto) {
        Long id = dto.idMedico();
        if (id != null) {
            return medicoRepository.getReferenceById(id);
        }
        var start = dto.data().minusHours(1);
        var end = dto.data().plusHours(1);
        return medicoRepository.findRandomMedicoAvailable(dto.especialidade(), start, end).orElseThrow(MedicoUnavailableException::new);
    }

    public void cancel(Long id, ConsultaCancelDto dto) {

    }
}
