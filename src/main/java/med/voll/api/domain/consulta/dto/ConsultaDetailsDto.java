package med.voll.api.domain.consulta.dto;

import java.time.LocalDateTime;

public record ConsultaDetailsDto(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime data
) {
}
