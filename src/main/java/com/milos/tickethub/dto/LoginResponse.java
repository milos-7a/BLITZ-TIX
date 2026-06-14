package com.milos.tickethub.dto;

public record LoginResponse (
        String token,
        UserResponse user
){ }
