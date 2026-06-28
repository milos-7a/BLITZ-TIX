package com.milos.blitztix.dto;

import com.milos.blitztix.entity.Role;
import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest(
        @NotNull
        Role role
) { }
