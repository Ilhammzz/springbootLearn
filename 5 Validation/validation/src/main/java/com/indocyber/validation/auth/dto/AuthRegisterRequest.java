package com.indocyber.validation.auth.dto;

import com.indocyber.validation.auth.validator.ComparePassword;
import com.indocyber.validation.auth.validator.Username;
import com.indocyber.validation.shared.validator.FieldMatch;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@ComparePassword
@FieldMatch(message = "First name dan Last name tidak sama", first = "firstName", second = "lastName")
public class AuthRegisterRequest {
    @NotNull
    @NotBlank(message = "Harus ada isinya untuk username!")
    @Username(message = "Username sudah digunakan")
    private final String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String confirmPassword;

    @NotBlank
    @Size(min = 5, max = 50)
    private final String firstName;

    @Size(max = 100)
    private final String lastName;

    @Past
    private final LocalDate birthDate;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    @Size(min = 10, max = 13)
    private final String phoneNumber;

    @NotBlank
    @Size(max = 2000)
    private final String address;

    @Size(min = 5, max = 5)
    private final String postalCode;

    @NotNull
    private final Boolean isMarried;

    @NotNull
    @Min(1)
    @Max(5)
    private final Integer yearsExperience;

    @NotNull
    @Min(0)
    @Max(2)
    private final Integer totalChildren;

    @NotNull
    @DecimalMin(value = "100")
    @DecimalMax(value = "999999")
    @Digits(integer = 12, fraction = 2, message = "Harus angka decimal dengan 2 angka belakang koma")
    private final BigDecimal estimatedSalary;
}
