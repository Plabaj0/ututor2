package com.codecool.social.ututor2back.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatInformation {
    @Id
    private UUID id = UUID.randomUUID();
    @NotBlank(message = "ThreadId cannot be empty")
    @Column(unique = true)
    private String threadId;
    @NotBlank(message = "AssistentId cannot be empty")
    private String assistantId;

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Chat_user_information",
            joinColumns = @JoinColumn(name = "chatId"),
            inverseJoinColumns = @JoinColumn(name = "appUserId")
    )


    private Set<AppUser> users = new HashSet<>();

    public ChatInformation(String threadId, String assistantId){
        this.threadId = threadId;
        this.assistantId = assistantId;

    }

    public void assignAppUser(AppUser appUser) {
        users.add(appUser);
    }
}
