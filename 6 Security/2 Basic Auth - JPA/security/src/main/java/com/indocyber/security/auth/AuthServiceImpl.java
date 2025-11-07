package com.indocyber.security.auth;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import com.indocyber.security.auth.dto.AuthRegisterResponse;
import com.indocyber.security.person.PersonMapper;
import com.indocyber.security.person.PersonRepository;
import com.indocyber.security.shared.exception.ResourceNotFoundException;
import com.indocyber.security.user.Role;
import com.indocyber.security.user.UserMapper;
import com.indocyber.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthRegisterResponse register(AuthRegisterRequest dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password tidak cocok");
        }
        var user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));           //encoded password
        user.setRole(Role.USER);                                                //default role

        var person = personMapper.toEntity(dto);
        person.setUser(user);
        user.setPerson(person);

        var savedUser = userRepository.save(user);
//        person.setUser(savedUser);
//        var savedPerson = personRepository.save(person);


        return authMapper.toRegisterResponse(savedUser);
    }

    public AuthRegisterResponse getByUsername(String username) {
        var user = userRepository.findById(username.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("No user found with username: " + username));
        return authMapper.toRegisterResponse(user);
    }
}
