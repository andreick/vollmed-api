package med.voll.api.domain.consulta.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.consulta.validation.validator.HorarioFuncionamentoValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = HorarioFuncionamentoValidator.class)
@Documented
public @interface HorarioFuncionamento {

    String message() default "deve estar dentro do horário de funcionamento da clínica (segunda a sábado, das 07:00 às 19:00)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
