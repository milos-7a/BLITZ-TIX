package com.milos.tickethub.controller;

import com.milos.tickethub.dto.LoginRequest;
import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody RegisterRequest request) {
        return authService.registerUser(request);
    }
    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }
}
