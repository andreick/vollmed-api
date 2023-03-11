package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.EnderecoMapper;
import med.voll.api.domain.medico.dto.MedicoCreateDto;
import med.voll.api.domain.medico.dto.MedicoDetailsDto;
import med.voll.api.domain.medico.dto.MedicoReadDto;
import med.voll.api.domain.medico.dto.MedicoUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    public Medico toMedico(MedicoCreateDto dto) {
        return new Medico(null, dto.nome(), dto.email(), dto.telefone(), dto.crm(), dto.especialidade(),
                enderecoMapper.toEndereco(dto.endereco()), true);
    }

    public Medico toMedico(Long id, MedicoUpdateDto dto) {
        return new Medico(id, dto.nome(), null, dto.telefone(), null, null,
                enderecoMapper.toEndereco(dto.endereco()), true);
    }

    public MedicoDetailsDto toDetailsDto(Medico medico) {
        return new MedicoDetailsDto(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(),
                medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }

    public MedicoReadDto toReadDto(Medico medico) {
        return new MedicoReadDto(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

    public Page<MedicoReadDto> toReadDto(Page<Medico> page) {
        return page.map(this::toReadDto);
    }
}
