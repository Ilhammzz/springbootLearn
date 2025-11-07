package com.indocyber.security.user;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)        // Set manually after encoding
    @Mapping(target = "registerDate", ignore = true)    // Set by @PrePersist
    @Mapping(target = "role", ignore = true)            // Set manually after determining default role
    @Mapping(target = "person", ignore = true)          // Set manually in service
    User toEntity(AuthRegisterRequest dto);
}
