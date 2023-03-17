package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void init() {
        cadastrarMedico("Medico Inativo", "medicoinativo@voll.med", "000000", Especialidade.CARDIOLOGIA, false);
        cadastrarMedico("Medico Ortopedia", "medicoortopedia@voll.med", "653789", Especialidade.ORTOPEDIA, true);
    }

    @Test
    @DisplayName("Retorna optional vazio quando nenhum médico da especialidade está disponível na data")
    void findRandomMedicoAvailable_NenhumDisponivel() {
        var cardiologia = Especialidade.CARDIOLOGIA;
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", cardiologia, true);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000", true);
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        cadastrarConsulta(paciente, medico, proximaSegundaAs10);

        var optMedico = medicoRepository.findRandomMedicoAvailable(cardiologia, proximaSegundaAs10.minusHours(1), proximaSegundaAs10.plusHours(1));

        assertThat(optMedico).isEmpty();
    }

    @Test
    @DisplayName("Retorna o médico da especialidade quando ele está disponível na data")
    void findRandomMedicoAvailable_Disponivel() {
        var cardiologia = Especialidade.CARDIOLOGIA;
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", cardiologia, true);
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var optMedico = medicoRepository.findRandomMedicoAvailable(cardiologia, proximaSegundaAs10.minusHours(1), proximaSegundaAs10.plusHours(1));

        assertThat(optMedico).hasValue(medico);
    }

    private Consulta cadastrarConsulta(Paciente paciente, Medico medico, LocalDateTime data) {
        return em.persist(new Consulta(null, paciente, medico, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade, boolean ativo) {
        var medico = new Medico(null, nome, email, "61999999999", crm, especialidade, endereco(), ativo);
        return em.persist(medico);
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf, boolean ativo) {
        var paciente = new Paciente(null, nome, email, "61999999999", cpf, endereco(), ativo);
        return em.persist(paciente);
    }

    private Endereco endereco() {
        return new Endereco(
                "Rua Xpto",
                null,
                null,
                "Bairro",
                "Brasilia",
                "DF",
                "00000000"
        );
    }
}
