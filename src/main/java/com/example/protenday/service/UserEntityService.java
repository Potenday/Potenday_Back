package com.example.protenday.service;

import com.example.protenday.domain.UserEntity;
import com.example.protenday.domain.constant.Role;
import com.example.protenday.dto.OAuthToken;
import com.example.protenday.dto.User;
import com.example.protenday.dto.request.UserRequest;
import com.example.protenday.dto.response.KakaoLoginResponseDto;
import com.example.protenday.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final KakaoOAuth2LoginService kakaoOAuth2LoginService;
    private final ForestEntityService forestEntityService;

    public void registerUser(UserRequest request) {
        UserEntity userEntity = userEntityRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullname(request.getFullname())
                .nickname(request.getNickname())
                .build());

        String forest = forestEntityService.createForest(userEntity);

        User user = User.fromEntity(userEntity, forest);
    }

    public void login(String code) {
        // 1. 토큰 정보 얻기
        OAuthToken oAuthToken = kakaoOAuth2LoginService.requestAccessToken(code);

        System.out.println(oAuthToken);

        // 2. 사용자 정보 얻기
        KakaoLoginResponseDto kakaoLoginResponseDto = kakaoOAuth2LoginService.requestUserInfo(oAuthToken.getAccess_token());

        // 3. 이미 회원가입된 유저인지 확인
        UserEntity userEntity = userEntityRepository.findByEmail(kakaoLoginResponseDto.getKakaoAccountDto().getEmail());

        // 4. 이미 가입된 유저인 경우
        if(!Objects.isNull(userEntity)) {

            String myForest = forestEntityService.getMyForest(userEntity);
//            return User.fromEntity(userEntity, myForest);
            return;
        }

        // 5. 신규 가입자인 경우
        UserEntity newUser = UserEntity.builder()
                .uuid(kakaoLoginResponseDto.getId())
                .email(kakaoLoginResponseDto.getKakaoAccountDto().getEmail())
                .password(kakaoLoginResponseDto.getKakaoAccountDto().getEmail())
                .nickname(kakaoLoginResponseDto.getKakaoAccountDto().getProfile().getNickname())
                .roles(Set.of(Role.USER))
                .build();

        userEntityRepository.save(newUser);

        String forest = forestEntityService.createForest(newUser);
        User user = User.fromEntity(newUser, forest);
    }

    public User loadUserByEmail(String email) {
        UserEntity userEntity = userEntityRepository.findByEmail(email);
        return User.fromEntity(userEntity);
    }
}
