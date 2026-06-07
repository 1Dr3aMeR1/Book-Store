package io.github.dr3amer1.backend.presentation.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}