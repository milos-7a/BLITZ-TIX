package com.milos.blitztix.controller;

import com.milos.blitztix.dto.LoginRequest;
import com.milos.blitztix.dto.LoginResponse;
import com.milos.blitztix.dto.RegisterRequest;
import com.milos.blitztix.dto.UserResponse;
import com.milos.blitztix.service.AuthService;
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
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }
}
