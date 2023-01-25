package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetailsDto> create(@RequestBody @Valid MedicoCreateDto dto, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dto);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDetailsDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoReadDto>> read(@PageableDefault(size = 20, sort = {"nome", "crm"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(MedicoReadDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetailsDto> update(@RequestBody @Valid MedicoUpdateDto dto) {
        var medico = repository.getReferenceById(dto.id());
        medico.update(dto);

        return ResponseEntity.ok(new MedicoDetailsDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetailsDto> readById(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDetailsDto(medico));
    }
}
