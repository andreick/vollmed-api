package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaMapper;
import med.voll.api.domain.consulta.ConsultaScheduleService;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaScheduleService consultaScheduleService;

    @Autowired
    private ConsultaMapper consultaMapper;

    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid ConsultaScheduleDto dto) {
        var consulta = consultaScheduleService.schedule(dto);
        return ResponseEntity.ok(consultaMapper.toDetailsDto(consulta));
    }
}
