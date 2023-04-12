package med.voll.api.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.validation.constraints.EnumConstant;

import java.util.stream.Stream;

public class EnumConstantValidator implements ConstraintValidator<EnumConstant, String> {

    private Enum<?>[] constants;

    @Override
    public void initialize(EnumConstant annotation) {
        constants = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Stream.of(constants).anyMatch(constant -> constant.name().equals(value));
    }
}
