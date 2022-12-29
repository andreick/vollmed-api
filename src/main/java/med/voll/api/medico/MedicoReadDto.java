package med.voll.api.medico;

public record MedicoReadDto(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public MedicoReadDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
