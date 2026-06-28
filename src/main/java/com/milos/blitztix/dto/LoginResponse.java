package com.milos.blitztix.dto;

public record LoginResponse (
        String token,
        UserResponse user
){ }
