package com.example.protenday.dto;

import com.example.protenday.domain.UserEntity;
import com.example.protenday.domain.constant.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private Set<Role> roles;
    private String fullname;
    private String nickname;
    private LocalDateTime registeredAt;
    private String registeredBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String forestUrl;

    public static User fromEntity(UserEntity entity) {
        return User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .roles(entity.getRoles())
                .fullname(entity.getFullname())
                .nickname(entity.getNickname())
                .registeredAt(entity.getRegisteredAt())
                .registeredBy(entity.getRegisteredBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public static User fromEntity(UserEntity entity, String forestUrl) {
        return User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .roles(entity.getRoles())
                .fullname(entity.getFullname())
                .nickname(entity.getNickname())
                .registeredAt(entity.getRegisteredAt())
                .registeredBy(entity.getRegisteredBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .forestUrl(forestUrl)
                .build();
    }
}
