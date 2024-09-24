package com.codecool.social.ututor2back.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
        @Size(min = 5, message = "name must be more than 5")
        String name,
        @NotBlank
        @Size(min = 8, message = "password must be more than 8")
        String password
) {
}
