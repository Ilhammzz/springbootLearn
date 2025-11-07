package com.indocyber.security.auth;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import com.indocyber.security.auth.dto.AuthRegisterResponse;
import com.indocyber.security.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final Map<String, AuthRegisterRequest> registeredUsers = new HashMap<>();
    private final AuthMapper authMapper;

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password tidak cocok");
        }
        registeredUsers.put(dto.getUsername().toLowerCase(), dto);

        return authMapper.toRegisterResponse(registeredUsers.get(dto.getUsername()));
    }

    @Override
    public AuthRegisterResponse getByUsername(String username) {
        if (!registeredUsers.containsKey(username.toLowerCase())) {
            throw new ResourceNotFoundException("No user found with username: " + username);
        }
        return authMapper.toRegisterResponse(registeredUsers.get(username));
    }
}
