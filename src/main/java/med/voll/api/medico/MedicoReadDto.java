package med.voll.api.medico;

public record MedicoReadDto(String nome, String email, String crm, Especialidade especialidade) {

    public MedicoReadDto(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
