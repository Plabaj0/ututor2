package com.codecool.social.ututor2back.mapper;

import com.codecool.social.ututor2back.controller.dto.ChatInformationDto;
import com.codecool.social.ututor2back.controller.dto.ChatInformationRequestDto;
import com.codecool.social.ututor2back.repository.AppUser;
import com.codecool.social.ututor2back.repository.ChatInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ChatInformationMapperTest {
    ChatInformationMapper chatInformationMapper = new ChatInformationMapper();

    @Test
    void shouldMapEntityToDto() {

        //given
        ChatInformation chatInformation = new ChatInformation("Asd", "Asd");
        AppUser appUser = new AppUser("Kamil", "asda");
        AppUser appUser1 = new AppUser("assdfg", "asasdda");
        chatInformation.assignAppUser(appUser);
        chatInformation.assignAppUser(appUser1);

        //when
        ChatInformationDto actualDto = chatInformationMapper.mapEntityToDto(chatInformation);

        //then
        Assertions.assertThat(actualDto.id()).isEqualTo(chatInformation.getId());
        Assertions.assertThat(actualDto.assistantId()).isEqualTo(chatInformation.getAssistantId());
        Assertions.assertThat(actualDto.threadId()).isEqualTo(chatInformation.getThreadId());
        Assertions.assertThat(actualDto.userDtos()).containsExactlyInAnyOrder(
                new ChatInformationDto.AppUserIdDto(appUser.getId()),
                new ChatInformationDto.AppUserIdDto(appUser1.getId())
        );
    }

    @Test
    void shouldMapDtoToEntity(){
        //given
        ChatInformationRequestDto testedRequestedDto = new ChatInformationRequestDto("asd", "Asd");


        //when
        ChatInformation actual =chatInformationMapper.mapDtoToEntity(testedRequestedDto);

        //then
        Assertions.assertThat(actual.getAssistantId()).isEqualTo("asd");
        Assertions.assertThat(actual.getThreadId()).isEqualTo("Asd");

    }

}