package com.milos.tickethub.dto;

import com.milos.tickethub.entity.Role;

import java.util.Set;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Set<Role> roles
) {}
