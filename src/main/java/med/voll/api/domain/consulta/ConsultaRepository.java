package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime start, LocalDateTime end);

    boolean existsByMedicoIdAndDataGreaterThanAndDataLessThan(Long id, LocalDateTime start, LocalDateTime end);
}
