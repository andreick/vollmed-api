package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaMapper;
import med.voll.api.domain.consulta.ConsultaScheduleService;
import med.voll.api.domain.consulta.dto.ConsultaCancelDto;
import med.voll.api.domain.consulta.dto.ConsultaDetailsDto;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaScheduleService consultaScheduleService;

    @Autowired
    private ConsultaMapper consultaMapper;

    @PostMapping
    public ResponseEntity<ConsultaDetailsDto> schedule(@RequestBody @Valid ConsultaScheduleDto dto) {
        var consulta = consultaScheduleService.schedule(dto);
        return ResponseEntity.ok(consultaMapper.toDetailsDto(consulta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id, @RequestBody @Valid ConsultaCancelDto dto) {
        consultaScheduleService.cancel(id, dto);
        return ResponseEntity.noContent().build();
    }
}
