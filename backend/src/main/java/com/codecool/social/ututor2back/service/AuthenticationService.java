package com.codecool.social.ututor2back.service;

import com.codecool.social.ututor2back.auth.AuthenticationResponse;
import com.codecool.social.ututor2back.controller.dto.UserRequestDto;
import com.codecool.social.ututor2back.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;



    public AuthenticationResponse authenticate(UserRequestDto request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.name(),
                        request.password()
                )
        );

        var user = userMapper.mapDtoToEntity(request);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userName", user.getName());
        var jwtToken = jwtService.generateToken(extraClaims,user);
        System.out.println(jwtToken + "token");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
