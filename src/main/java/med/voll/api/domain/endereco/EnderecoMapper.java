package med.voll.api.domain.endereco;

import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEndereco(EnderecoDto enderecoDto) {
        return new Endereco(enderecoDto);
    }

    public EnderecoDto toDto(Endereco endereco) {
        return new EnderecoDto(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
    }
}
