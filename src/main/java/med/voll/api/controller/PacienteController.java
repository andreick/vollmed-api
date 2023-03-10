package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.PacienteMapper;
import med.voll.api.domain.paciente.PacienteService;
import med.voll.api.domain.paciente.dto.PacienteCreateDto;
import med.voll.api.domain.paciente.dto.PacienteDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteMapper pacienteMapper;

    @PostMapping
    public ResponseEntity<PacienteDetailsDto> create(@RequestBody @Valid PacienteCreateDto dto, UriComponentsBuilder uriBuilder) {
        var paciente = pacienteService.save(pacienteMapper.toEntity(dto));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(pacienteMapper.toDto(paciente));
    }
}
