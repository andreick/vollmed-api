package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoCreateDto;
import med.voll.api.medico.MedicoReadDto;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid MedicoCreateDto dto) {
        repository.save(new Medico(dto));
    }

    @GetMapping
    public Page<MedicoReadDto> read(@PageableDefault(size = 20, sort = {"nome", "crm"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return repository.findAll(pageable).map(MedicoReadDto::new);
    }
}
