package com.indocyber.security.auth.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_PREFIX_LENGTH = 7;

    private final AuthenticationFailureHandler failureHandler;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            // 1. Extract JWT token from Authorization header
            String token = extractTokenFromRequest(request);

            // 2. If no token found, continue without authentication
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 3. Extract username from token
            String username = jwtService.extractUsername(token);

            // 4. If username exists and user is not already authenticated
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                authenticateUser(request, token, username);
            }

            // 5. Continue the filter chain
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("JWT authentication failed: {}", e.getMessage());
            failureHandler.onAuthenticationFailure(
                    request,
                    response,
                    new BadCredentialsException("Invalid or expired JWT token", e)
            );
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(BEARER_PREFIX_LENGTH);
        }

        return null;
    }

    private void authenticateUser(HttpServletRequest request, String token, String username) {
        // 1. Load user details from database
        var userDetails = userDetailsService.loadUserByUsername(username);

        // 2. Validate token
        if (jwtService.isValid(token, userDetails.getUsername())) {
            // 3. Create authentication token
            var authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            // 4. Set additional details (IP address, session ID, etc.) if needed
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            // 5. Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            log.debug("User '{}' authenticated successfully", username);
        }
    }
}