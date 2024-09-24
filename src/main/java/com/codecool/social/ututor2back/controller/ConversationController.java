package com.codecool.social.ututor2back.controller;

import com.codecool.social.ututor2back.controller.dto.ChatInformationDto;
import com.codecool.social.ututor2back.mapper.ChatInformationMapper;
import com.codecool.social.ututor2back.mapper.UserMapper;
import com.codecool.social.ututor2back.repository.AppUser;
import com.codecool.social.ututor2back.repository.ChatInformation;
import com.codecool.social.ututor2back.repository.ChatInformationRepository;
import com.codecool.social.ututor2back.repository.UserRepository;
import com.codecool.social.ututor2back.service.ChatInformationService;
import com.codecool.social.ututor2back.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chats")
@CrossOrigin(origins = "http://localhost:3000")
public class ConversationController {
    private final ChatInformationMapper chatInformationMapper; // nie powinno się tu znajdować tylko w serwisie

    private final ChatInformationRepository chatInformationRepository; // nie powinno się tu znajdować tylko w serwisie

    private final UserRepository userRepository; // nie powinno się tu znajdować tylko w serwisie


    public ConversationController(ChatInformationMapper chatInformationMapper, ChatInformationRepository chatInformationRepository, UserMapper userMapper, ChatInformationService chatInformationService, UserService userService, UserRepository userRepository, UserMapper userMapper1) {
        this.chatInformationMapper = chatInformationMapper;
        this.chatInformationRepository = chatInformationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{name}/ChatInformation")
    public ChatInformationDto getFirstChatInformation(@PathVariable String name) {
        Optional<AppUser> appUserBef = userRepository.findByName(name);
        Pageable pageable = PageRequest.of(0, 1);
        List<ChatInformation> chatInformation = chatInformationRepository.findByNameWithLatestChatInformations(appUserBef.get().getName(), pageable);

        return chatInformationMapper.mapEntityToDto(chatInformation.get(0));
    }
}
