package com.example.protenday.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogoutRequest {

    private String accessToken;
    private String refreshToken;
}
