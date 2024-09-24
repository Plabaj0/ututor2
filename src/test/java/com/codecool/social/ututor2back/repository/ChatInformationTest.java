package com.codecool.social.ututor2back.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ChatInformationTest {

    AppUser testedAppUser = new AppUser("kamil", "123");


    @Test
    public void shouldAssignAppUser() {
        // given
        ChatInformation testedChatINformation = new ChatInformation("123", "123");
        // when
        testedAppUser.assignChatInformation(testedChatINformation);

        // then
        Assertions.assertThat(testedAppUser.getChats().size()).isEqualTo(1);
    }

}
