package com.indocyber.security.auth.dto;

import lombok.Data;

@Data
public class AuthTokenRequest {
    private final String username;
    private final String password;
}
