package com.codecool.social.ututor2back.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatInformationRepository extends JpaRepository<ChatInformation, UUID> {

//    @Query("SELECT c FROM ChatInformation c ORDER BY c.createdAt DESC")
//    List<ChatInformation> findLatestChatInformation(Pageable pageable);

    @Query("SELECT c FROM AppUser u " +
            "LEFT JOIN u.chats c " +
            "WHERE u.name = :name " +
            "ORDER BY c.createdAt DESC")
    List<ChatInformation> findByNameWithLatestChatInformations(@Param("name") String name, Pageable pageable);


    @Query("SELECT c FROM AppUser u " +
            "LEFT JOIN u.chats c " +
            "WHERE u.name = :name ")
    List<ChatInformation> findByNameAllChats(@Param("name") String name);

    @Modifying
    @Query("DELETE FROM ChatInformation d WHERE d.id = :id")
    void deleteChatInformationById(@Param("id") UUID id);
}
