package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Medico")
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 6, unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private boolean ativo;

    public void update(Medico medico) {
        nome = Objects.requireNonNullElse(medico.getNome(), nome);
        telefone = Objects.requireNonNullElse(medico.getTelefone(), telefone);
        endereco = Objects.requireNonNullElse(medico.getEndereco(), endereco);
    }

    public void delete() {
        ativo = false;
    }
}
