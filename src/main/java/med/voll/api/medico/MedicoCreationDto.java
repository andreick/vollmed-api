package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.EnderecoDto;

public record MedicoCreationDto(

        @NotBlank
        String nome,

        @NotNull
        @Email
        String email,

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
