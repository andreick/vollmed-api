package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.EnderecoDto;
import med.voll.api.domain.endereco.EnderecoMapper;
import med.voll.api.domain.paciente.dto.PacienteCreateDto;
import med.voll.api.domain.paciente.dto.PacienteDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    public Paciente toEntity(PacienteCreateDto dto) {
        return new Paciente(null, dto.nome(), dto.email(), dto.telefone(), dto.cpf(),
                enderecoMapper.toEndereco(dto.endereco()), true);
    }

    public PacienteDetailsDto toDto(Paciente paciente) {
        return new PacienteDetailsDto(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),
                paciente.getCpf(), enderecoMapper.toDto(paciente.getEndereco()));
    }
}
