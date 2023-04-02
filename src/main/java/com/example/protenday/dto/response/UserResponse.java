package com.example.protenday.dto.response;

import com.example.protenday.dto.TokenInfo;
import com.example.protenday.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String nickname;
    private String forestUrl;
    private String accessToken;
    private String refreshToken;

    public static UserResponse of(User user, TokenInfo tokenInfo) {
        return UserResponse.builder()
                .nickname(user.getNickname())
                .forestUrl(user.getForestUrl())
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
    }
}
