package com.milos.tickethub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String location,
        @NotNull
        LocalDateTime dateTime
) {
}
