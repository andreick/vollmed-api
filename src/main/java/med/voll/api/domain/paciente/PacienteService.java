package med.voll.api.domain.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente save(Paciente newPaciente) {
        return pacienteRepository.save(newPaciente);
    }
}
