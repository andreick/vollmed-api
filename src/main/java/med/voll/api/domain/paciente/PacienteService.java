package med.voll.api.domain.paciente;

import med.voll.api.domain.paciente.exception.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Paciente save(Paciente newPaciente) {
        return pacienteRepository.save(newPaciente);
    }

    public Page<Paciente> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Page<Paciente> findAllAtivo(Pageable pageable) {
        return pacienteRepository.findByAtivoTrue(pageable);
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
    }

    public Paciente findByIdAtivo(Long id) {
        return pacienteRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new PacienteNotFoundException(id));
    }

    @Transactional
    public Paciente update(Long id, Paciente updatedPaciente) {
        var paciente = findByIdAtivo(id);
        paciente.update(updatedPaciente);
        return paciente;
    }

    @Transactional
    public void softDelete(Long id) {
        var paciente = findById(id);
        paciente.delete();
    }
}
