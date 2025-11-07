package com.indocyber.security.auth;

import com.indocyber.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findById(username.toLowerCase());

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("No User found with username: " + username);
        }
        var user = optionalUser.get();

        return new AuthUserDetails(user);
    }
}
