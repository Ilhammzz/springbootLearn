package com.indocyber.validation.auth;

import com.indocyber.validation.auth.dto.AuthRegisterRequest;
import com.indocyber.validation.auth.dto.AuthRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthRegisterResponse toRegisterResponse(AuthRegisterRequest dto);
}
