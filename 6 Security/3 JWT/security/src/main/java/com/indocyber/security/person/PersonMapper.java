package com.indocyber.security.person;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "username", ignore = true)
    Person toEntity(AuthRegisterRequest dto);
}
