package com.indocyber.validation.auth.validator;

import com.indocyber.validation.auth.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final AuthService authService;

    @Override
    public boolean isValid(String inputUsername, ConstraintValidatorContext constraintValidatorContext) {
        return !authService.userExistsByUsername(inputUsername);
    }
}
