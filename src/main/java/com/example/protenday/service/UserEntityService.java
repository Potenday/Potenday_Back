package com.example.protenday.service;

import com.example.protenday.domain.UserEntity;
import com.example.protenday.domain.constant.Role;
import com.example.protenday.dto.OAuthToken;
import com.example.protenday.dto.request.UserRequest;
import com.example.protenday.dto.response.KakaoLoginResponseDto;
import com.example.protenday.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .password(kakaoLoginResponseDto.getKakaoAccountDto().getEmail())
                .nickname(kakaoLoginResponseDto.getKakaoAccountDto().getProfile().getNickname())
                .roles(Set.of(Role.USER))
                .build();

        userEntityRepository.save(userEntity);
    }
}
