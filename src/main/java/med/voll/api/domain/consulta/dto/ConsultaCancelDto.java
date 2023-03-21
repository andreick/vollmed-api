package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.MotivoCancelamento;

public record ConsultaCancelDto(

        @NotNull
        MotivoCancelamento motivo
) {
}
