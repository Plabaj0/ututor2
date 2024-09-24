package com.codecool.social.ututor2back.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatInformationRequestDto(
        @NotBlank
        String threadId,
        @NotBlank
        String assistantId
)

{
}
