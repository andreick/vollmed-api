package med.voll.api.domain.paciente;

import med.voll.api.domain.paciente.exception.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente save(Paciente newPaciente) {
        return pacienteRepository.save(newPaciente);
    }

    public Page<Paciente> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
    }
}
