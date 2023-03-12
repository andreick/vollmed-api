package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record ConsultaScheduleDto(

        @NotNull
        Long idPaciente,

        Long idMedico,

        Especialidade especialidade,

        @NotNull
        @Future
        LocalDateTime data
) {
}
