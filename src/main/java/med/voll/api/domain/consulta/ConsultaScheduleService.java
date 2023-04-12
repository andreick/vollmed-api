package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.dto.ConsultaCancelDto;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.consulta.exception.ConsultaNotFoundException;
import med.voll.api.domain.consulta.validation.ConsultaScheduleValidation;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.exception.MedicoUnavailableException;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.system.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Transactional
    public Consulta schedule(ConsultaScheduleDto dto) {
        validations.forEach(validation -> validation.validate(dto));
        var medico = getAvailableMedico(dto);
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        return consultaRepository.save(new Consulta(null, paciente, medico, dto.data(), StatusConsulta.AGENDADO));
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

    @Transactional
    public void cancel(Long id, ConsultaCancelDto dto) {
        var consulta = consultaRepository.findById(id).orElseThrow(() -> new ConsultaNotFoundException(id));
        var now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        boolean lessThan24Hours = now.isBefore(consulta.getData().plusHours(24).truncatedTo(ChronoUnit.MINUTES));
        if (lessThan24Hours) {
            throw new BadRequestException("A consulta não pode ser cancelada com menos de 24 horas de antecedência");
        }
        consulta.cancelar(dto.motivo());
    }
}
