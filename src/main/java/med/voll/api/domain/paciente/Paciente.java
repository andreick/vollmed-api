package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private boolean ativo;

    public void update(Paciente paciente) {
        nome = Objects.requireNonNullElse(paciente.getNome(), nome);
        telefone = Objects.requireNonNullElse(paciente.getTelefone(), telefone);
        endereco = Objects.requireNonNullElse(paciente.getEndereco(), endereco);
    }

    public void delete() {
        ativo = false;
    }
}
