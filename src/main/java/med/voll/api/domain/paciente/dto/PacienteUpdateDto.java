package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;
import med.voll.api.domain.endereco.EnderecoDto;
import med.voll.api.validation.constraints.NullOrNotBlank;
import med.voll.api.validation.constraints.TelefoneSemMascara;

public record PacienteUpdateDto(

        @NullOrNotBlank
        String nome,

        @TelefoneSemMascara
        String telefone,

        @Valid
        EnderecoDto endereco
) {
}
