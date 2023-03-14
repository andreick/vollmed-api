package med.voll.api.domain.consulta.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.domain.consulta.validation.constraints.HorarioFuncionamento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class HorarioFuncionamentoValidator implements ConstraintValidator<HorarioFuncionamento, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime time, ConstraintValidatorContext context) {
        if (time == null) {
            return true;
        }
        boolean isSunday = time.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = time.getHour() < 7;
        boolean afterClosing = time.getHour() > 18;
        return !(isSunday || beforeOpening || afterClosing);
    }
}
