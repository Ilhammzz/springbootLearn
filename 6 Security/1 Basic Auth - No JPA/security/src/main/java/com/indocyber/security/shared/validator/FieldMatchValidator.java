package com.indocyber.security.shared.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String first;
    private String second;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            var firstValue = getValueFromField(object, first);
            var secondValue = getValueFromField(object, second);

            return (firstValue == null && secondValue == null) || (firstValue != null && firstValue.equals(secondValue));
        } catch (Exception e) {
            return false;
        }
    }

    private Object getValueFromField(Object object,
                                     String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
