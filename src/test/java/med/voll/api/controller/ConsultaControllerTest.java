package med.voll.api.controller;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaScheduleService;
import med.voll.api.domain.consulta.dto.ConsultaDetailsDto;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.system.exception.handler.ValidationErrorResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ValidationErrorResponse> validationErrorResponseJson;

    @Autowired
    private JacksonTester<ConsultaScheduleDto> consultaScheduleDtoJson;

    @Autowired
    private JacksonTester<ConsultaDetailsDto> consultaDetailsDtoJson;

    @MockBean
    private ConsultaScheduleService consultaScheduleService;

    @Test
    @WithMockUser
    @DisplayName("O id do Paciente e a data da consulta são obrigatórios")
    void schedule_BadRequest_Obrigatorio() throws Exception {
        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        var validationErrorResponse = validationErrorResponseJson.parseObject(response.getContentAsString());
        SoftAssertions.assertSoftly((assertions) -> {
            assertions.assertThat(validationErrorResponse.getErrors()).anyMatch(error -> error.getField().equals("idPaciente"));
            assertions.assertThat(validationErrorResponse.getErrors()).anyMatch(error -> error.getField().equals("data"));
        });
    }

    @Test
    @WithMockUser
    @DisplayName("A data da consulta não pode ser no domingo")
    void schedule_BadRequest_HorarioFuncionamento_Domingo() throws Exception {
        var proximoDomingoAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .atTime(10, 0);
        var consultaScheduleDto = new ConsultaScheduleDto(1L, 1L, null, proximoDomingoAs10);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaScheduleDtoJson.write(consultaScheduleDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        var validationErrorResponse = validationErrorResponseJson.parseObject(response.getContentAsString());
        assertThat(validationErrorResponse.getErrors()).anyMatch(error -> error.getField().equals("data"));
    }

    @Test
    @WithMockUser
    @DisplayName("O horário da consulta deve estar dentro do horário de funcionamento da clínica")
    void schedule_BadRequest_HorarioFuncionamento_AntesDas7() throws Exception {
        var proximaSextaAs6e59 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .atTime(6, 59);
        var consultaScheduleDto = new ConsultaScheduleDto(1L, 1L, null, proximaSextaAs6e59);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaScheduleDtoJson.write(consultaScheduleDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        var validationErrorResponse = validationErrorResponseJson.parseObject(response.getContentAsString());
        assertThat(validationErrorResponse.getErrors()).anyMatch(error -> error.getField().equals("data"));
    }

    @Test
    @WithMockUser
    @DisplayName("O horário da consulta deve estar dentro do horário de funcionamento da clínica")
    void schedule_BadRequest_HorarioFuncionamento_DepoisDas18() throws Exception {
        var proximaSegundaAs18e01 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(18, 1);
        var consultaScheduleDto = new ConsultaScheduleDto(1L, 1L, null, proximaSegundaAs18e01);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaScheduleDtoJson.write(consultaScheduleDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        var validationErrorResponse = validationErrorResponseJson.parseObject(response.getContentAsString());
        assertThat(validationErrorResponse.getErrors()).anyMatch(error -> error.getField().equals("data"));
    }

    @Test
    @WithMockUser
    @DisplayName("A consulta é agendada com no mínimo 30 minutos de antecedência")
    void schedule_BadRequest_HorarioAntecedencia() throws Exception {
        var proximos30Minutos = LocalDateTime.now().plusMinutes(30);
        var consultaScheduleDto = new ConsultaScheduleDto(1L, 1L, null, proximos30Minutos);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaScheduleDtoJson.write(consultaScheduleDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        var validationErrorResponse = validationErrorResponseJson.parseObject(response.getContentAsString());
        assertThat(validationErrorResponse.getErrors()).anyMatch(error -> error.getField().equals("data"));
    }

    @Test
    @WithMockUser
    @DisplayName("Deve retornar os detalhes da consulta agendada")
    void schedule_Ok() throws Exception {
        var proximoSabadoAs18 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .atTime(18, 0);
        var consultaScheduleDto = new ConsultaScheduleDto(1L, 1L, null, proximoSabadoAs18);
        var consultaDetailsDto = new ConsultaDetailsDto(1L, 1L, 1L, proximoSabadoAs18);

        when(consultaScheduleService.schedule(any())).thenReturn(new Consulta(1L, paciente(), medico(), proximoSabadoAs18));

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaScheduleDtoJson.write(consultaScheduleDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(consultaDetailsDtoJson.write(consultaDetailsDto).getJson());
    }

    private Paciente paciente() {
        return new Paciente(1L, null, null, null, null, null, false);
    }

    private Medico medico() {
        return new Medico(1L, null, null, null, null, null, null, false);
    }
}
