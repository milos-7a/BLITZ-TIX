package com.milos.blitztix.dto;

import com.milos.blitztix.entity.Role;

import java.util.Set;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Set<Role> roles
) {}
