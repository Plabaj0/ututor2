package com.codecool.social.ututor2back.controller;

import com.codecool.social.ututor2back.auth.AuthenticationResponse;
import com.codecool.social.ututor2back.controller.dto.UserRequestDto;
import com.codecool.social.ututor2back.mapper.UserMapper;
import com.codecool.social.ututor2back.repository.UserRepository;
import com.codecool.social.ututor2back.service.AuthenticationService;
import com.codecool.social.ututor2back.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/user")
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserController(AuthenticationService authenticationService, UserMapper userMapper, UserRepository userRepository, UserService userService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(
            @RequestBody UserRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/newUser")
    public void createUser(@RequestBody UserRequestDto request) {
        var user = userMapper.mapDtoToEntity(request);
        userRepository.save(user);
    }
}
