package com.codecool.social.ututor2back.service;

import com.codecool.social.ututor2back.controller.dto.UserDto;
import com.codecool.social.ututor2back.controller.dto.UserRequestDto;
import com.codecool.social.ututor2back.mapper.UserMapper;
import com.codecool.social.ututor2back.repository.ChatInformationRepository;
import com.codecool.social.ututor2back.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final ChatInformationRepository chatInformationRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ChatInformationRepository chatInformationRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.chatInformationRepository = chatInformationRepository;
        this.userMapper = userMapper;
    }
    public UserDto saveNewUser(UserRequestDto dto) {
        var newUserStory = userMapper.mapDtoToEntity(dto);
        var savedUserStory = userRepository.save(newUserStory);
        return userMapper.mapEntityToDto(savedUserStory);
    }

//    public UserDto loadUserByUsername(String userName) {
//        return userRepository.findAll().stream()
//                .filter(user -> userName.equals(user.getUsername()))
//                .map(userMapper::mapDtoToEntity)
//                .findFirst()
//                .orElse(null);
//    }
}