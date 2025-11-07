package com.indocyber.security.auth.validator;

import com.indocyber.security.auth.AuthService;
import com.indocyber.security.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String inputUsername, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsById(inputUsername.toLowerCase());
    }
}
