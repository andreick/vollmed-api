package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDto;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.validation.constraints.TelefoneSemMascara;

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
