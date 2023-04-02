package com.example.protenday.dto;

import com.example.protenday.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private String fullname;
    private String nickname;
    private String roles;
    private LocalDateTime registeredAt;
    private String registeredBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private LocalDateTime deletedAt;
    private String forestUrl;

    public static User fromEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fullname(entity.getFullname())
                .nickname(entity.getNickname())
                .roles(entity.getRoles().toString())
                .registeredAt(entity.getRegisteredAt())
                .registeredBy(entity.getRegisteredBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public static User fromEntity(UserEntity entity, String forestUrl) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fullname(entity.getFullname())
                .nickname(entity.getNickname())
                .roles(entity.getRoles().toString())
                .registeredAt(entity.getRegisteredAt())
                .registeredBy(entity.getRegisteredBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .deletedAt(entity.getDeletedAt())
                .forestUrl(forestUrl)
                .build();
    }

    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(roles.contains("[") && roles.contains("]")) {
            roles = roles.substring(1, roles.length()-1);
        }

        for(String role : roles.split(",")){
            authorities.add(new SimpleGrantedAuthority(role.trim()));
        }

        return authorities;
    }

    @Override
    public String getUsername() { return email;}

    @Override @JsonIgnore public boolean isAccountNonExpired() { return this.deletedAt == null; }
    @Override @JsonIgnore public boolean isAccountNonLocked() { return this.deletedAt == null; }
    @Override @JsonIgnore public boolean isCredentialsNonExpired() { return this.deletedAt == null; }
    @Override @JsonIgnore public boolean isEnabled() { return this.deletedAt == null; }

}
