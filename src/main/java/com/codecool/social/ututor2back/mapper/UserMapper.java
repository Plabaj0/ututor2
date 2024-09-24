package com.codecool.social.ututor2back.mapper;

import com.codecool.social.ututor2back.controller.dto.UserDto;
import com.codecool.social.ututor2back.controller.dto.UserRequestDto;
import com.codecool.social.ututor2back.repository.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDto mapEntityToDto(AppUser entity) {
        return new UserDto(
                entity.getId(),
                entity.getName(),
                entity.getPassword(),
                entity.getChats().stream()
                        .map(d -> new UserDto.ChatInformationId(
                                d.getId()
                        ))
                        .toList()

        );

    }

    public AppUser mapDtoToEntity(UserRequestDto dto) {
        return new AppUser(
                dto.name(),
                passwordEncoder.encode(dto.password())
        );
    }
}
