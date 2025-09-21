package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.core.jwt.JwtUtil;
import com.libraryapp.librarymanagementapp.dto.user.request.LoginRequest;
import com.libraryapp.librarymanagementapp.dto.user.request.RegisterRequest;
import com.libraryapp.librarymanagementapp.dto.user.response.LoginResponse;
import com.libraryapp.librarymanagementapp.dto.user.response.RegisteredResponse;
import com.libraryapp.librarymanagementapp.entity.User;
import jakarta.persistence.Column;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password",
            expression = "java(passwordEncoder.encode(registerRequest.getPassword()))")
    User registerRequestToUser(@Context PasswordEncoder passwordEncoder, RegisterRequest registerRequest);



    RegisteredResponse userToRegisterResponse(User user);

    User loginRequestToUser(LoginRequest loginRequest);
    LoginResponse userToLoginResponse(User user);
}
