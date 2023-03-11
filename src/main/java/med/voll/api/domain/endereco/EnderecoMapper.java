package med.voll.api.domain.endereco;

import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEndereco(EnderecoDto dto) {
        return dto != null ? new Endereco(dto.logradouro(), dto.numero(), dto.complemento(), dto.bairro(), dto.cidade(),
                dto.uf(), dto.cep()) : null;
    }

    public EnderecoDto toDto(Endereco endereco) {
        return new EnderecoDto(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
    }
}
