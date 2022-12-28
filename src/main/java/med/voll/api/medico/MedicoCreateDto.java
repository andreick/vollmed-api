package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.EnderecoDto;

public record MedicoCreateDto(

        @NotBlank
        String nome,

        @NotNull
        @Email
        String email,

        @NotNull
        @Pattern(regexp = "[1-9]{2}(9[1-9]|[2-8])\\d{7}")
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
