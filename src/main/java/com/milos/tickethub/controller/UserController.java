package com.milos.tickethub.controller;

import com.milos.tickethub.dto.RegisterRequest;
import com.milos.tickethub.dto.UserResponse;
import com.milos.tickethub.entity.User;
import com.milos.tickethub.mapper.UserMapper;
import com.milos.tickethub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody RegisterRequest request) {
        User user = userService.registerUser(request);
        return UserMapper.toResponse(user);
    }
}
