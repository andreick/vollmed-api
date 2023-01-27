package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.validation.constraints.TelefoneSemMascara;
import med.voll.api.domain.endereco.EnderecoDto;

public record MedicoCreateDto(

        @NotBlank
        String nome,

        @NotNull
        @Email
        String email,

        @NotNull
        @TelefoneSemMascara
        String telefone,

        @NotNull
        @Pattern(regexp = "\\d{6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        EnderecoDto endereco
) {
}