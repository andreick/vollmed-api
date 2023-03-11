package med.voll.api.domain.medico;

import med.voll.api.domain.paciente.exception.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    public Medico save(Medico newMedico) {
        return repository.save(newMedico);
    }

    public Page<Medico> findAllAtivo(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    public Medico findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
    }

    public Medico findByIdAtivo(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new PacienteNotFoundException(id));
    }

    @Transactional
    public Medico update(Medico updatedMedico) {
        var medico = findByIdAtivo(updatedMedico.getId());
        medico.update(updatedMedico);
        return medico;
    }

    @Transactional
    public void softDelete(Long id) {
        var medico = findById(id);
        medico.delete();
    }
}
