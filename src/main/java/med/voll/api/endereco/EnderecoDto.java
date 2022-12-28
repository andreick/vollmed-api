package med.voll.api.endereco;

public record EnderecoDto(String logradouro, String numero, String complemento, String bairro, String cidade,
                          String uf, String cep) {
}
