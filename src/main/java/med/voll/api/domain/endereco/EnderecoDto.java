package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.validation.constraints.CepSemMascara;

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
        @CepSemMascara
        String cep
) {
}
