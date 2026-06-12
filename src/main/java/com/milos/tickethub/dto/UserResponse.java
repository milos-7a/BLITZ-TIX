package com.milos.tickethub.dto;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {}
