package com.indocyber.security.auth.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AuthRegisterResponse {
    private final String username;
}
