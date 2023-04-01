package com.example.potenday.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccountDto {

    @JsonProperty("profile")
    private KakaoProfileDto profile;

    @JsonProperty("email")
    private String email;
}
