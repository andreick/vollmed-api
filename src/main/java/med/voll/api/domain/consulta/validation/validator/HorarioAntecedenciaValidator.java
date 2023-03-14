package med.voll.api.domain.consulta.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.domain.consulta.validation.constraints.HorarioAntecedencia;

import java.time.LocalDateTime;

public class HorarioAntecedenciaValidator implements ConstraintValidator<HorarioAntecedencia, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime time, ConstraintValidatorContext context) {
        if (time == null) {
            return true;
        }
        return time.isAfter(LocalDateTime.now().plusMinutes(30));
    }
}
