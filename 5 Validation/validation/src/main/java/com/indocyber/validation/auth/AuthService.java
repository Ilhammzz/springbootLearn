package com.indocyber.validation.auth;

import com.indocyber.validation.auth.dto.AuthRegisterRequest;
import com.indocyber.validation.auth.dto.AuthRegisterResponse;

public interface AuthService {

    AuthRegisterResponse register(AuthRegisterRequest dto);

    AuthRegisterResponse getByUsername(String username);

    boolean userExistsByUsername(String username);
}
