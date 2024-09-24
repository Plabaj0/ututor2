package com.codecool.social.ututor2back.controller.dto;

import java.util.List;
import java.util.UUID;

public record ChatInformationDto(
        UUID id,
        String assistantId,
        String threadId,
        List<AppUserIdDto> userDtos
) {
    public record AppUserIdDto(
            UUID id
    ) {}
}
