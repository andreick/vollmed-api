package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDto;
import med.voll.api.domain.validation.constraints.TelefoneSemMascara;

public record PacienteCreateDto(

        @NotBlank
        String nome,

        @NotNull
        @Email
        String email,

        @NotNull
        @TelefoneSemMascara
        String telefone,

        @NotNull
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull
        @Valid
        EnderecoDto endereco
) {
}
