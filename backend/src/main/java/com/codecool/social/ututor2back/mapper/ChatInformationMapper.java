package com.codecool.social.ututor2back.mapper;

import com.codecool.social.ututor2back.controller.dto.ChatInformationDto;
import com.codecool.social.ututor2back.controller.dto.ChatInformationRequestDto;
import com.codecool.social.ututor2back.repository.ChatInformation;
import org.springframework.stereotype.Component;

@Component
public class ChatInformationMapper {
    public ChatInformationDto mapEntityToDto(ChatInformation entity) {
        return new ChatInformationDto(
                entity.getId(),
                entity.getThreadId(),
                entity.getAssistantId(),
                entity.getUsers().stream()
                        .map(us -> new ChatInformationDto.AppUserIdDto(
                                us.getId()
                        ))
                        .toList()
        );
    }

    public ChatInformation mapDtoToEntity(ChatInformationRequestDto dto) {
        return new ChatInformation(
                dto.assistantId(),
                dto.threadId()
        );
    }
}
