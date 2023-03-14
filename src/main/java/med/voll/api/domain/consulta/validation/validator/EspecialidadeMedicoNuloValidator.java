package med.voll.api.domain.consulta.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.domain.consulta.dto.ConsultaScheduleDto;
import med.voll.api.domain.consulta.validation.constraints.EspecialidadeMedicoNulo;

public class EspecialidadeMedicoNuloValidator implements ConstraintValidator<EspecialidadeMedicoNulo, ConsultaScheduleDto> {

    @Override
    public boolean isValid(ConsultaScheduleDto dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }
        if (dto.idMedico() == null) {
            return dto.especialidade() != null;
        }
        return true;
    }
}
