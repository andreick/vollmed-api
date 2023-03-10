package med.voll.api.domain.paciente.dto;

public record PacienteReadDto(
        String nome,
        String email,
        String cpf
) {
}
