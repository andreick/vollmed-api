package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Consulta")
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false, length = 25)
    private StatusConsulta status;

    @Column(name = "motivo_cancelamento", length = 50)
    private MotivoCancelamento motivoCancelamento;

    public Consulta(Long id, Paciente paciente, Medico medico, LocalDateTime data, StatusConsulta status) {
        this(id, paciente, medico, data, status, null);
    }

    public void cancelar(MotivoCancelamento motivoCancelamento) {
        status = StatusConsulta.CANCELADO;
        this.motivoCancelamento = motivoCancelamento;
    }
}
