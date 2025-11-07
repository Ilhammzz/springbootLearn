package com.indocyber.security.auth.dto;

import com.indocyber.security.user.Role;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRegisterResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private LocalDate registerDate;
}