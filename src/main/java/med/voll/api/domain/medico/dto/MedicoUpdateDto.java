package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.validation.constraints.NullOrNotBlank;
import med.voll.api.domain.validation.constraints.TelefoneSemMascara;
import med.voll.api.domain.endereco.EnderecoDto;

public record MedicoUpdateDto(

        @NotNull
        Long id,

        @NullOrNotBlank
        String nome,

        @TelefoneSemMascara
        String telefone,

        @Valid
        EnderecoDto endereco
) {
}
