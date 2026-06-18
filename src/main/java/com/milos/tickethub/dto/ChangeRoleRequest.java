package com.milos.tickethub.dto;

import com.milos.tickethub.entity.Role;
import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest(
        @NotNull
        Role role
) { }
