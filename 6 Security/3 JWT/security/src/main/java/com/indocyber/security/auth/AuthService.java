package com.indocyber.security.auth;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import com.indocyber.security.auth.dto.AuthRegisterResponse;
import com.indocyber.security.auth.dto.AuthTokenRequest;
import com.indocyber.security.auth.dto.AuthTokenResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    AuthTokenResponse register(AuthRegisterRequest dto);

    AuthRegisterResponse getByUsername(String username);

    AuthTokenResponse createToken(AuthTokenRequest dto);

    AuthTokenResponse getToken(Authentication authentication);
}
