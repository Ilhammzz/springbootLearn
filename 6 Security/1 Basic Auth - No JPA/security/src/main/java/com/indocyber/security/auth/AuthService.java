package com.indocyber.security.auth;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import com.indocyber.security.auth.dto.AuthRegisterResponse;

public interface AuthService {

    AuthRegisterResponse register(AuthRegisterRequest dto);

    AuthRegisterResponse getByUsername(String username);
}
