package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaScheduleService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta schedule(ConsultaScheduleDto dto) {
        System.out.println(dto);
        return new Consulta(null, new Paciente(), new Medico(), null);
    }
}
