package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.dto.ConsultaCancelDto;
import med.voll.api.domain.consulta.exception.ConsultaNotFoundException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.system.exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaScheduleServiceTest {

    @InjectMocks
    private ConsultaScheduleService consultaScheduleService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    @DisplayName("A consulta pode ser cancelada")
    void cancel_Ok() {
        var consulta = new Consulta(1L, new Paciente(), new Medico(), LocalDateTime.now().minusHours(24), StatusConsulta.AGENDADO);
        var optConsulta = Optional.of(consulta);
        when(consultaRepository.findById(consulta.getId())).thenReturn(optConsulta);

        consultaScheduleService.cancel(consulta.getId(), new ConsultaCancelDto(MotivoCancelamento.PACIENTE_DESISTIU));

        assertSoftly(softly -> {
            softly.assertThat(consulta.getStatus()).as("Status incorreto").isEqualTo(StatusConsulta.CANCELADO);
            softly.assertThat(consulta.getMotivoCancelamento()).as("Motivo incorreto").isEqualTo(MotivoCancelamento.PACIENTE_DESISTIU);
        });
    }

    @Test
    @DisplayName("Uma ConsultaNotFoundException é lançada se a consulta não existir")
    void cancel_ConsultaNotFound() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ConsultaNotFoundException.class)
                .as("A ConsultaNotFoundException não foi lançada")
                .isThrownBy(() -> consultaScheduleService.cancel(1L, new ConsultaCancelDto(MotivoCancelamento.OUTROS)));
    }

    @Test
    @DisplayName("A consulta somente poderá ser cancelada com antecedência mínima de 24 horas")
    void cancel_Antecedencia() {
        var consultaCancelDto = new ConsultaCancelDto(MotivoCancelamento.PACIENTE_DESISTIU);
        var ultimas24Horas = LocalDateTime.now().minusHours(23).minusMinutes(59);
        var consulta = new Consulta(1L, new Paciente(), new Medico(), ultimas24Horas, StatusConsulta.AGENDADO);
        var optConsulta = Optional.of(consulta);
        when(consultaRepository.findById(consulta.getId())).thenReturn(optConsulta);

        assertThatExceptionOfType(BadRequestException.class)
                .as("A BadRequestException não foi lançada")
                .isThrownBy(() -> consultaScheduleService.cancel(consulta.getId(), consultaCancelDto));
    }
}
