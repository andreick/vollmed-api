package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.EnderecoMapper;
import med.voll.api.domain.paciente.dto.PacienteCreateDto;
import med.voll.api.domain.paciente.dto.PacienteDetailsDto;
import med.voll.api.domain.paciente.dto.PacienteReadDto;
import med.voll.api.domain.paciente.dto.PacienteUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    public Paciente toPaciente(PacienteCreateDto dto) {
        return new Paciente(null, dto.nome(), dto.email(), dto.telefone(), dto.cpf(),
                enderecoMapper.toEndereco(dto.endereco()), true);
    }

    public Paciente toPaciente(PacienteUpdateDto dto) {
        var endereco = dto.endereco() != null ? enderecoMapper.toEndereco(dto.endereco()) : null;
        return new Paciente(null, dto.nome(), null, dto.telefone(), null, endereco, true);
    }

    public PacienteDetailsDto toDetailsDto(Paciente paciente) {
        return new PacienteDetailsDto(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),
                paciente.getCpf(), enderecoMapper.toDto(paciente.getEndereco()));
    }

    public PacienteReadDto toReadDto(Paciente paciente) {
        return new PacienteReadDto(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

    public Page<PacienteReadDto> toReadDto(Page<Paciente> pacientes) {
        return pacientes.map(this::toReadDto);
    }
}
