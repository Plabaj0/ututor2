package com.codecool.social.ututor2back.service;

import com.codecool.social.ututor2back.controller.dto.ChatInformationDto;
import com.codecool.social.ututor2back.mapper.ChatInformationMapper;
import com.codecool.social.ututor2back.repository.AppUser;
import com.codecool.social.ututor2back.repository.ChatInformation;
import com.codecool.social.ututor2back.repository.ChatInformationRepository;
import com.codecool.social.ututor2back.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatInformationService {

    private final ChatInformationMapper chatInformationMapper;
    private final ChatInformationRepository chatInformationRepository;

    private final UserRepository userRepository;


    public ChatInformationService(ChatInformationMapper chatInformationMapper, ChatInformationRepository chatInformationRepository, ChatInformationRepository userRepository, UserRepository userRepository1) {
        this.chatInformationMapper = chatInformationMapper;
        this.chatInformationRepository = chatInformationRepository;
        this.userRepository = userRepository1;
    }

    public List<ChatInformationDto> getChatInformation() {
        return chatInformationRepository.findAll().stream()
                .map(chatInformationMapper::mapEntityToDto)
                .toList();
    }


    @Transactional
    public void assignUserStoryToDeveloper(UUID userStoryId, UUID developerId) {

        Optional<AppUser> appUserOpt = userRepository.findById(userStoryId);
        Optional<ChatInformation> chatInformationOpt = chatInformationRepository.findById(developerId);

        if (appUserOpt.isPresent() && chatInformationOpt.isPresent()) {
            AppUser appUser = appUserOpt.get();
            ChatInformation chatInformation = chatInformationOpt.get();


            appUser.assignChatInformation(chatInformation);
            chatInformation.assignAppUser(appUser);

            userRepository.save(appUser);
            chatInformationRepository.save(chatInformation);
        }
    }
}
