package com.codecool.social.ututor2back.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AppUserTest {

    @Test
    void shouldReturnPassword() {
        // given
        AppUser testedAppUser = new AppUser("kamil", "123");

        // when
        String actual = testedAppUser.getPassword();

        // then
        Assertions.assertThat(actual).isEqualTo(testedAppUser.getPassword());
    }

    @Test
    void shouldAssignChatInformation() {
        // given
        ChatInformation testedChatInformation = new ChatInformation("asd", "Asd");
        AppUser appUser = new AppUser("Kamil", "aslfk");

        // when
        testedChatInformation.assignAppUser(appUser);

        // then
        Assertions.assertThat(testedChatInformation.getUsers().size()).isEqualTo(1);
    }
}