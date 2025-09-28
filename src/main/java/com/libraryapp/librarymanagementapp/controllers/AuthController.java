package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.user.request.LoginRequest;
import com.libraryapp.librarymanagementapp.dto.user.request.RegisterRequest;
import com.libraryapp.librarymanagementapp.dto.user.response.LoginResponse;
import com.libraryapp.librarymanagementapp.dto.user.response.RegisteredResponse;
import com.libraryapp.librarymanagementapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/library/users")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public RegisteredResponse register(@Valid @RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
