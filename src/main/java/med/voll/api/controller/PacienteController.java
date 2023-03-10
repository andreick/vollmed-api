package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.PacienteMapper;
import med.voll.api.domain.paciente.PacienteService;
import med.voll.api.domain.paciente.dto.PacienteCreateDto;
import med.voll.api.domain.paciente.dto.PacienteDetailsDto;
import med.voll.api.domain.paciente.dto.PacienteReadDto;
import med.voll.api.domain.paciente.dto.PacienteUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        var paciente = pacienteService.save(pacienteMapper.toPaciente(dto));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(pacienteMapper.toDetailsDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteReadDto>> readAll(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        var pacientes = pacienteService.findAllAtivo(pageable);
        return ResponseEntity.ok(pacienteMapper.toReadDto(pacientes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteReadDto> readOne(@PathVariable Long id) {
        var paciente = pacienteService.findByIdAtivo(id);
        return ResponseEntity.ok(pacienteMapper.toReadDto(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDetailsDto> update(@PathVariable Long id, @RequestBody @Valid PacienteUpdateDto dto) {
        var paciente = pacienteService.update(id, pacienteMapper.toPaciente(dto));
        return ResponseEntity.ok(pacienteMapper.toDetailsDto(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
