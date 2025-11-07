package com.indocyber.security.auth;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import com.indocyber.security.auth.dto.AuthRegisterResponse;
import com.indocyber.security.auth.dto.AuthTokenRequest;
import com.indocyber.security.auth.dto.AuthTokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping()
    public ResponseEntity<AuthTokenResponse> getToken(Authentication authentication) {
        return ResponseEntity.ok(authService.getToken(authentication));
    }

    @PostMapping("create-token")
    public ResponseEntity<AuthTokenResponse> createToken(@Valid @RequestBody AuthTokenRequest dto) {
        return ResponseEntity.ok(authService.createToken(dto));
    }

    @GetMapping("{username}")
    public ResponseEntity<AuthRegisterResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.getByUsername(username));
    }

    @PostMapping("register")
    public ResponseEntity<AuthTokenResponse> register(@Valid @RequestBody AuthRegisterRequest dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @GetMapping("admin-role")
    public ResponseEntity<String> adminRole() {
        return ResponseEntity.ok("Admin Authorized");
    }

    @GetMapping("user-role")
    public ResponseEntity<String> userRole() {
        return ResponseEntity.ok("User Authorized");
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("{username}/user-specific")
    public ResponseEntity<String> userSpecific(@PathVariable String username) {
        return ResponseEntity.ok("user specific: " + username);
    }
}
