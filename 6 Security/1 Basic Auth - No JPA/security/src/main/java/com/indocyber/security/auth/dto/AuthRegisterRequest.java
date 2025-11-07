package com.indocyber.security.auth.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AuthRegisterRequest {
    private final String username;
    private final String password;
    private final String confirmPassword;
}
