package med.voll.api.domain.consulta.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.domain.consulta.validation.constraints.HorarioFuncionamento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HorarioFuncionamentoValidator implements ConstraintValidator<HorarioFuncionamento, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime time, ConstraintValidatorContext context) {
        if (time == null) {
            return true;
        }
        boolean isSunday = time.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = time.getHour() < 7;
        boolean afterClosing = time.isAfter(time.with(LocalTime.of(18, 0)));
        return !(isSunday || beforeOpening || afterClosing);
    }
}
