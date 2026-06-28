package com.milos.blitztix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PasswordChangeRequest(
        @NotNull
        String oldPassword,
        @NotNull
        @Size(min = 6)
        String newPassword
) {
}
