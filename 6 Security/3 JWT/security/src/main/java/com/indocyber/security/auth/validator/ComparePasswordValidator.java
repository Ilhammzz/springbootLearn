package com.indocyber.security.auth.validator;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePasswordValidator implements ConstraintValidator<ComparePassword, AuthRegisterRequest> {

    @Override
    public boolean isValid(AuthRegisterRequest dto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return dto.getPassword().equals(dto.getConfirmPassword());
    }
}