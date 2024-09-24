package com.codecool.social.ututor2back.controller.dto;

import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String password,
        List<ChatInformationId> chatInformationDtos
) {
    public record ChatInformationId(
      UUID id
    ){}
    }