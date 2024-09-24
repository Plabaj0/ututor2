package com.codecool.social.ututor2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByName(String name);

    @Query("SELECT u FROM AppUser u LEFT JOIN FETCH u.chats WHERE u.name = :name")
    Optional<AppUser> findByNameWithChatInformations(@Param("name") String name);

}
