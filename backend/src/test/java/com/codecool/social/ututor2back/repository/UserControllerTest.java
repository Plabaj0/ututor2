package com.codecool.social.ututor2back.repository;

import com.codecool.social.ututor2back.config.JwtAuthenticationFilter;
import com.codecool.social.ututor2back.config.SecurityConfig;
import com.codecool.social.ututor2back.config.SecurityConfiguration;
import com.codecool.social.ututor2back.controller.UserController;
import com.codecool.social.ututor2back.controller.dto.UserDto;
import com.codecool.social.ututor2back.controller.dto.UserRequestDto;
import com.codecool.social.ututor2back.mapper.UserMapper;
import com.codecool.social.ututor2back.service.AuthenticationService;
import com.codecool.social.ututor2back.service.JwtService;
import com.codecool.social.ututor2back.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.UUID;



@Import({JwtService.class, SecurityConfig.class, SecurityConfiguration.class, JwtAuthenticationFilter.class, AuthenticationService.class})
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    @WithMockUser()
    void shouldReturnNewAppUser() throws Exception {
        // given
        UserRequestDto userRequestDto = new UserRequestDto("Kamil", "123");

        UserDto userDto = new UserDto(UUID.randomUUID(), "Kamil", "123", new ArrayList<>());

        Mockito.when(userService.saveNewUser(userRequestDto))
                .thenReturn(userDto);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Kamil",
                            "password": "123"
                        }
                        """));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(userDto.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(userDto.password()));
    }
}