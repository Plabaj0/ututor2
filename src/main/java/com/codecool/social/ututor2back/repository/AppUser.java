package com.codecool.social.ututor2back.repository;

import com.codecool.social.ututor2back.repository.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppUser implements UserDetails {
    @Id
    private UUID id = UUID.randomUUID();

    @NotBlank(message = "Name cannot be empty")
    @EqualsAndHashCode.Include
    private String name;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<ChatInformation> chats = new HashSet<>();

    public AppUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public void assignChatInformation(ChatInformation chatInformation) {
        chats.add(chatInformation);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new
                SimpleGrantedAuthority(role.USER.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}