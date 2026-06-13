package com.milos.tickethub.dto;

public record ErrorResponse(
        String message,
        java.util.Map<String, String> errors) { }
