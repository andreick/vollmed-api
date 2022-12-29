package med.voll.api.annotation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Pattern(regexp = "[1-9]{2}(9[1-9]|[2-8])\\d{7}")
@ReportAsSingleViolation
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface TelefoneSemMascara {

    String message() default "deve ser um telefone válido sem máscara de 10 ou 11 dígitos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
