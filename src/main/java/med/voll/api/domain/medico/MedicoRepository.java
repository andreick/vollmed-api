package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    Optional<Medico> findByIdAndAtivoTrue(Long id);

    boolean existsByIdAndAtivoTrue(Long id);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.ativo = true
            AND m.especialidade = :especialidade
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE c.data > :start AND c.data < :end
            )
            ORDER BY RAND()
            LIMIT 1""")
    Optional<Medico> findRandomMedicoAvailable(Especialidade especialidade, LocalDateTime start, LocalDateTime end);
}
