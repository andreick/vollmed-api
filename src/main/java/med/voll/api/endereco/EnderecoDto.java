package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(

        @NotBlank
        String logradouro,

        String numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotNull
        @Pattern(regexp = "[A-Z]{2}")
        String uf,

        @NotNull
        @Pattern(regexp = "\\d{5}-\\d{3}")
        String cep
) {
}
