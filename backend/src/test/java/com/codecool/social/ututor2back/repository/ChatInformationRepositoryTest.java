package com.codecool.social.ututor2back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ChatInformationRepositoryTest {

    @Autowired
    private ChatInformationRepository testedChatInformationRepository;

//    @Test
//    void shouldReturnListOfCreatedAtDescChatInformation() {
//        //given
//        ChatInformation chatInformation1 = new ChatInformation();
//        ChatInformation chatInformation2 = new ChatInformation();
//        ChatInformation chatInformation3 = new ChatInformation();
//
//        Pageable pageable = PageRequest.of(0, 1);
//
//        List<ChatInformation> chatInformations = new ArrayList<>(Arrays.asList(chatInformation1, chatInformation2, chatInformation3));
//        //when
//        List<ChatInformation> actual = testedChatInformationRepository.findLatestChatInformation(pageable);
//        //then
//        Assertions.assertThat(actual.get(0)).isEqualTo(chatInformation1);
//        Assertions.assertThat(actual.get(0)).isEqualTo(chatInformation2);
//        Assertions.assertThat(actual.get(0)).isEqualTo(chatInformation3);
//    }

//    @Test
//    void shouldReturnChatsAssignToUserDesc() {
//        // given
//        AppUser appUser = new AppUser("Kamil", "asd");
//        ChatInformation chatInformation1 = new ChatInformation("231asf", "asd123");
//        appUser.assignChatInformation(chatInformation1);
//        chatInformation1.assignAppUser(appUser);
//        testedChatInformationRepository.save(chatInformation1);
//
//        Pageable pageable = PageRequest.of(0, 2);
//
//        // when
//        List<ChatInformation> actual = testedChatInformationRepository.findByNameWithLatestChatInformations(appUser.getName(), pageable);
//
//        // then
//        Assertions.assertThat(actual).hasSize(2);
//        Assertions.assertThat(actual.get(1)).isEqualTo(chatInformation1);
//    }

}
