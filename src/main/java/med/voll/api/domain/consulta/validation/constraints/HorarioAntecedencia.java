package med.voll.api.domain.consulta.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.consulta.validation.validator.HorarioAntecedenciaValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = HorarioAntecedenciaValidator.class)
@Documented
public @interface HorarioAntecedencia {

    String message() default "deve ter no mínimo 30 minutos de antecedência";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
