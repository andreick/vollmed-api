package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.MedicoMapper;
import med.voll.api.domain.medico.MedicoService;
import med.voll.api.domain.medico.dto.MedicoCreateDto;
import med.voll.api.domain.medico.dto.MedicoDetailsDto;
import med.voll.api.domain.medico.dto.MedicoReadDto;
import med.voll.api.domain.medico.dto.MedicoUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoMapper medicoMapper;

    @PostMapping
    public ResponseEntity<MedicoDetailsDto> create(@RequestBody @Valid MedicoCreateDto dto, UriComponentsBuilder uriBuilder) {
        var medico = medicoService.save(medicoMapper.toMedico(dto));
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medicoMapper.toDetailsDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoReadDto>> readAll(@PageableDefault(size = 20, sort = {"nome", "crm"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        var page = medicoService.findAllAtivo(pageable);
        return ResponseEntity.ok(medicoMapper.toReadDto(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetailsDto> readOne(@PathVariable Long id) {
        var medico = medicoService.findByIdAtivo(id);
        return ResponseEntity.ok(medicoMapper.toDetailsDto(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDetailsDto> update(@PathVariable Long id, @RequestBody @Valid MedicoUpdateDto dto) {
        var medico = medicoService.update(medicoMapper.toMedico(id, dto));
        return ResponseEntity.ok(medicoMapper.toDetailsDto(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
