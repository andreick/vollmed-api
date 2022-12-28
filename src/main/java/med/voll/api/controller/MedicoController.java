package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoCreateDto;
import med.voll.api.medico.MedicoReadDto;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<MedicoReadDto> read() {
        return repository.findAll().stream().map(MedicoReadDto::new).toList();
    }
}
