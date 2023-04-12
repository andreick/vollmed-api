package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.validation.constraints.EspecialidadeMedicoNulo;
import med.voll.api.domain.consulta.validation.constraints.HorarioAntecedencia;
import med.voll.api.domain.consulta.validation.constraints.HorarioFuncionamento;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

@EspecialidadeMedicoNulo
public record ConsultaScheduleDto(

        @NotNull
        Long idPaciente,

        Long idMedico,

        Especialidade especialidade,

        @NotNull
        @HorarioFuncionamento
        @HorarioAntecedencia
        LocalDateTime data
) {
}
