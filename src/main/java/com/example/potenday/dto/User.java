package com.example.potenday.dto;

import com.example.potenday.domain.constant.Role;
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

    public static User from(String email, String password, Set<Role> roles, String fullname, String nickname, LocalDateTime registeredAt, String registeredBy, LocalDateTime modifiedAt, String modifiedBy) {
        return User.builder()
                .email(email)
                .password(password)
                .roles(roles)
                .fullname(fullname)
                .nickname(nickname)
                .registeredAt(registeredAt)
                .registeredBy(registeredBy)
                .modifiedAt(modifiedAt)
                .modifiedBy(modifiedBy)
                .build();
    }
}
