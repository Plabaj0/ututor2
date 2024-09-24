package com.codecool.social.ututor2back.controller;

import com.codecool.social.ututor2back.controller.dto.ChatInformationDto;
import com.codecool.social.ututor2back.controller.dto.ChatInformationRequestDto;
import com.codecool.social.ututor2back.mapper.ChatInformationMapper;
import com.codecool.social.ututor2back.mapper.UserMapper;
import com.codecool.social.ututor2back.repository.AppUser;
import com.codecool.social.ututor2back.repository.ChatInformationRepository;
import com.codecool.social.ututor2back.repository.UserRepository;
import com.codecool.social.ututor2back.service.ChatInformationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/developer")
public class ChatInformationController {
    private final ChatInformationService chatInformationService;
    private final ChatInformationMapper chatInformationMapper; // nie powinno się tu znajdować tylko w serwisie
    private final ChatInformationRepository chatInformationRepository; // nie powinno się tu znajdować tylko w serwisie
    private final UserRepository userRepository; // nie powinno się tu znajdować tylko w serwisie

    public ChatInformationController(ChatInformationService chatInformationService, ChatInformationMapper chatInformationMapper, ChatInformationRepository chatInformationRepository, UserRepository userRepository, UserMapper userMapper) {
        this.chatInformationService = chatInformationService;
        this.chatInformationMapper = chatInformationMapper;
        this.chatInformationRepository = chatInformationRepository;
        this.userRepository = userRepository;
    }

//    @GetMapping
//    public List<ChatInformationDto> getAllChatInformation(){
//        return chatInformationService.getChatInformation();
//    }

    @PostMapping("/{name}/newChat")
    public void createChatInformation(@RequestBody ChatInformationRequestDto request, @PathVariable String name) {
        System.out.println("newChat");
        var chatInformation = chatInformationMapper.mapDtoToEntity(request);
        var username = userRepository.findByName(name);
        AppUser appUser = username.get();

        chatInformationRepository.save(chatInformation);
        chatInformationService.assignUserStoryToDeveloper(appUser.getId(), chatInformation.getId());
    }

    @PostMapping("/{name}/selectChat")
    public List<ChatInformationDto> getAllChats(@PathVariable String name) {
        return chatInformationRepository.findByNameAllChats(name).stream()
                .map(chatInformationMapper::mapEntityToDto)
                .collect(Collectors.toList()); // nie powinno się tu znajdować tylko w serwisie
    }
}