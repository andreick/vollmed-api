package med.voll.api.domain.consulta.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import med.voll.api.domain.consulta.validation.validator.EspecialidadeMedicoNuloValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {EspecialidadeMedicoNuloValidator.class})
@Documented
public @interface EspecialidadeMedicoNulo {

    String message() default "não deve ser nulo se o id do médico for nulo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
