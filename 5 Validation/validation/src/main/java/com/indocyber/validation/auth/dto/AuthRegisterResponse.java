package com.indocyber.validation.auth.dto;

import com.indocyber.validation.auth.validator.Username;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AuthRegisterResponse {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String postalCode;
    private final Boolean isMarried;
    private final Integer yearsExperience;
    private final Integer totalChildren;
    private final BigDecimal estimatedSalary;
}
