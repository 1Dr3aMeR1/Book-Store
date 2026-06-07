package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.domain.model.Role;
import io.github.dr3amer1.backend.domain.model.UserEntity;
import io.github.dr3amer1.backend.domain.repository.UserRepository;
import io.github.dr3amer1.backend.presentation.dto.auth.AuthResponse;
import io.github.dr3amer1.backend.presentation.dto.auth.LoginRequest;
import io.github.dr3amer1.backend.presentation.dto.auth.RegisterRequest;
import io.github.dr3amer1.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new IllegalArgumentException(
                    "Email already exists");
        }

        UserEntity user = new UserEntity();

        user.setEmail(request.getEmail());
        user.setPasswordHash(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new AuthResponse(
                jwtService.generateToken(user)
        );
    }

    public AuthResponse login(LoginRequest request) {

        UserEntity user =
                userRepository.findByEmail(
                                request.getEmail())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new IllegalArgumentException(
                    "Invalid credentials");
        }

        return new AuthResponse(
                jwtService.generateToken(user)
        );
    }
}