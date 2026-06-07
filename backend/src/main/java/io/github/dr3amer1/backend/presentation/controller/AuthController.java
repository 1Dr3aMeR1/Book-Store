package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.AuthenticationService;
import io.github.dr3amer1.backend.presentation.dto.auth.AuthResponse;
import io.github.dr3amer1.backend.presentation.dto.auth.LoginRequest;
import io.github.dr3amer1.backend.presentation.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")

    public AuthResponse register(
            @RequestBody RegisterRequest request) {

        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authenticationService.login(request);
    }
}