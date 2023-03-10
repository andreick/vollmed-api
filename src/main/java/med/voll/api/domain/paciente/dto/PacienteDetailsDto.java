package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.EnderecoDto;

public record PacienteDetailsDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDto endereco
) {
}
