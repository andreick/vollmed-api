package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.dto.MedicoCreateDto;
import med.voll.api.domain.medico.dto.MedicoUpdateDto;

@Table(name = "medicos")
@Entity(name = "Medico")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(MedicoCreateDto dto) {
        nome = dto.nome();
        email = dto.email();
        telefone = dto.telefone();
        crm = dto.crm();
        especialidade = dto.especialidade();
        endereco = new Endereco(dto.endereco());
        ativo = true;
    }

    public void update(MedicoUpdateDto dto) {
        if (dto.nome() != null) {
            nome = dto.nome();
        }
        if (dto.telefone() != null) {
            telefone = dto.telefone();
        }
        if (dto.endereco() != null) {
            endereco = new Endereco(dto.endereco());
        }
    }

    public void delete() {
        ativo = false;
    }
}
