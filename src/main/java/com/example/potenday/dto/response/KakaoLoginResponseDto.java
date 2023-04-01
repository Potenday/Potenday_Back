package com.example.potenday.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoLoginResponseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccountDto kakaoAccountDto;

    @JsonProperty("properties")
    private KakaoPropertyDto properties;
}
