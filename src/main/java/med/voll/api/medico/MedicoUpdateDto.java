package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.annotation.constraints.NullOrNotBlank;
import med.voll.api.annotation.constraints.TelefoneSemMascara;
import med.voll.api.endereco.EnderecoDto;

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
