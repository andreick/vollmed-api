package med.voll.api.domain.paciente.dto;

public record PacienteReadDto(
        Long id,
        String nome,
        String email,
        String cpf
) {
}
