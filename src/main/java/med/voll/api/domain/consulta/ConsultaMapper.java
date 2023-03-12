package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.dto.ConsultaDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    public ConsultaDetailsDto toDetailsDto(Consulta consulta) {
        return new ConsultaDetailsDto(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData());
    }
}
