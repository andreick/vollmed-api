package med.voll.api.controller;

import med.voll.api.medico.MedicoCreationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void create(@RequestBody MedicoCreationData data) {
        System.out.println(data);
    }
}
