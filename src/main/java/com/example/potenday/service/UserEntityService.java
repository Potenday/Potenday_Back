package com.example.potenday.service;

import com.example.potenday.domain.UserEntity;
import com.example.potenday.domain.constant.Role;
import com.example.potenday.dto.OAuthToken;
import com.example.potenday.dto.request.UserRequest;
import com.example.potenday.dto.response.KakaoLoginResponseDto;
import com.example.potenday.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final KakaoOAuth2LoginService kakaoOAuth2LoginService;

    public void registerUser(UserRequest request) {
        userEntityRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullname(request.getFullname())
                .nickname(request.getNickname())
                .build());
    }

    public void login(String code) {
        // 1. 토큰 정보 얻기
        OAuthToken oAuthToken = kakaoOAuth2LoginService.requestAccessToken(code);

        System.out.println(oAuthToken);

        // 2. 사용자 정보 얻기
        KakaoLoginResponseDto kakaoLoginResponseDto = kakaoOAuth2LoginService.requestUserInfo(oAuthToken.getAccess_token());

        UserEntity userEntity = UserEntity.builder()
                .uuid(kakaoLoginResponseDto.getId())
                .email(kakaoLoginResponseDto.getKakaoAccountDto().getEmail())
                .password(kakaoLoginResponseDto.getNickname())
                .nickname(kakaoLoginResponseDto.getNickname())
                .roles(Set.of(Role.USER))
                .build();

        userEntityRepository.save(userEntity);
    }
}
