package com.indocyber.validation.auth;

import com.indocyber.validation.auth.dto.AuthRegisterRequest;
import com.indocyber.validation.auth.dto.AuthRegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("{username}")
    public ResponseEntity<AuthRegisterResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.getByUsername(username));
    }

    @PostMapping("register")
    public ResponseEntity<AuthRegisterResponse> register(@Valid @RequestBody AuthRegisterRequest dto) {
        return ResponseEntity.ok(authService.register(dto));
    }
}
