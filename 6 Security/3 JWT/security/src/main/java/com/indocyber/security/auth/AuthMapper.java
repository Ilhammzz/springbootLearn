package com.indocyber.security.auth;

import com.indocyber.security.auth.dto.AuthRegisterRequest;
import com.indocyber.security.auth.dto.AuthRegisterResponse;
import com.indocyber.security.auth.dto.AuthTokenResponse;
import com.indocyber.security.person.Person;
import com.indocyber.security.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "email", source = "person.email")
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "registerDate", source = "user.registerDate")
    AuthRegisterResponse toRegisterResponse(User user);
}