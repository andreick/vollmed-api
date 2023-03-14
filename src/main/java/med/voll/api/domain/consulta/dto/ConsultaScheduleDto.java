package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.validation.constraints.EspecialidadeMedicoNulo;
import med.voll.api.domain.consulta.validation.constraints.HorarioAntecedencia;
import med.voll.api.domain.consulta.validation.constraints.HorarioFuncionamento;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.validation.constraints.EnumConstant;

import java.time.LocalDateTime;

@EspecialidadeMedicoNulo
public record ConsultaScheduleDto(

        @NotNull
        Long idPaciente,

        Long idMedico,

        @EnumConstant(enumClass = Especialidade.class, message = "deve ser uma especialidade válida")
        String especialidade,

        @NotNull
        @HorarioFuncionamento
        @HorarioAntecedencia
        LocalDateTime data
) {
}
