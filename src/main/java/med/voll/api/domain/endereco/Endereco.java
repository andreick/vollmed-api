package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoDto dto) {
        logradouro = dto.logradouro();
        numero = dto.numero();
        complemento = dto.complemento();
        bairro = dto.bairro();
        cidade = dto.cidade();
        uf = dto.uf();
        cep = dto.cep();
    }
}
