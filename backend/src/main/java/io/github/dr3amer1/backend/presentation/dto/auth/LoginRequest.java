package io.github.dr3amer1.backend.presentation.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}