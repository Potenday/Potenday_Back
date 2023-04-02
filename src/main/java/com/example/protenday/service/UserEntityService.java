package com.example.protenday.service;

import com.example.protenday.domain.UserEntity;
import com.example.protenday.domain.constant.Role;
import com.example.protenday.dto.OAuthToken;
import com.example.protenday.dto.TokenInfo;
import com.example.protenday.dto.User;
import com.example.protenday.dto.request.UserRequest;
import com.example.protenday.dto.response.KakaoLoginResponseDto;
import com.example.protenday.dto.response.UserResponse;
import com.example.protenday.exception.ErrorCode;
import com.example.protenday.exception.PotendayApplicationException;
import com.example.protenday.repository.UserEntityCacheRepository;
import com.example.protenday.repository.UserEntityRepository;
import com.example.protenday.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final KakaoOAuth2LoginService kakaoOAuth2LoginService;
    private final ForestEntityService forestEntityService;
    private final BCryptPasswordEncoder encoder;
    private final UserEntityCacheRepository userEntityCacheRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Transactional
    public void registerUser(UserRequest request) {
        UserEntity userEntity = userEntityRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .fullname(request.getFullname())
                .nickname(request.getNickname())
                .roles(Set.of(Role.USER))
                .build());

        String forest = forestEntityService.createForest(userEntity);

        User user = User.fromEntity(userEntity, forest);
    }

    public UserResponse login(String code) {
        User user = null;
        // 1. 토큰 정보 얻기
        OAuthToken oAuthToken = kakaoOAuth2LoginService.requestAccessToken(code);

        System.out.println(oAuthToken);

        // 2. 사용자 정보 얻기
        KakaoLoginResponseDto kakaoLoginResponseDto = kakaoOAuth2LoginService.requestUserInfo(oAuthToken.getAccess_token());

        // 3. 이미 회원가입된 유저인지 확인
        UserEntity userEntity = userEntityRepository.findByEmail(kakaoLoginResponseDto.getKakaoAccountDto().getEmail()).get();

        if(!Objects.isNull(userEntity)) {
            // 4. 이미 가입된 유저인 경우
            String myForest = forestEntityService.getMyForest(userEntity);
            user = User.fromEntity(userEntity, myForest);
        }else {
            // 5. 신규 가입자인 경우
            UserEntity newUser = UserEntity.builder()
                    .uuid(kakaoLoginResponseDto.getId())
                    .email(kakaoLoginResponseDto.getKakaoAccountDto().getEmail())
                    .password(encoder.encode(kakaoLoginResponseDto.getId().toString()))
                    .fullname(kakaoLoginResponseDto.getKakaoAccountDto().getProfile().getNickname())
                    .nickname(kakaoLoginResponseDto.getKakaoAccountDto().getProfile().getNickname())
                    .roles(Set.of(Role.USER))
                    .build();

            userEntityRepository.save(newUser);
            String forest = forestEntityService.createForest(newUser);
            user = User.fromEntity(newUser, forest);
        }


        TokenInfo tokenInfo = JwtTokenUtils.createTokenInfo(user, secretKey);
        userEntityCacheRepository.setUser(user);
        userEntityCacheRepository.setRefreshToken(user.getEmail(), tokenInfo.getRefreshToken());

        return UserResponse.of(user, tokenInfo);
    }

    public User loadUserByEmail(String email) {
        return userEntityCacheRepository.getUser(email).orElseGet(() ->
            userEntityRepository.findByEmail(email).map(User::fromEntity).orElseThrow(() ->
                new PotendayApplicationException(ErrorCode.USER_NOT_FOUND))
        );
    }

    public void logout(String email) {
        userEntityCacheRepository.deleteRefreshToken(email);
    }

    public TokenInfo reissue(String accessToken, String refreshToken) {
        String email = JwtTokenUtils.getEmail(accessToken, secretKey);
        String refreshTokenFromRedis = userEntityCacheRepository.getRefreshToken(email);
        User user = (User) loadUserByEmail(email);

        if (ObjectUtils.isEmpty(refreshTokenFromRedis)) {
            throw new PotendayApplicationException(ErrorCode.INVALID_TOKEN, "Invalid Access Token");
        }

        if (!refreshToken.equals(refreshTokenFromRedis.substring(1, refreshTokenFromRedis.length() - 1))) {
            throw new PotendayApplicationException(ErrorCode.INVALID_PERMISSION, "Refresh Token is invalid.");
        }

        return JwtTokenUtils.reIssue(user, secretKey, refreshToken);
    }
}
