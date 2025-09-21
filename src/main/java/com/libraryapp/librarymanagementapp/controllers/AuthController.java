package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.user.request.RegisterRequest;
import com.libraryapp.librarymanagementapp.dto.user.response.RegisteredResponse;
import com.libraryapp.librarymanagementapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
