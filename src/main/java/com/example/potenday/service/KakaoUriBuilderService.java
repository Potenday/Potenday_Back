package com.example.potenday.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoUriBuilderService {

    private static final String KAKAO_REQUEST_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_REQUEST_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"

    public URI buildUriFromKakaoToken() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_REQUEST_TOKEN_URL);

        URI uri = uriBuilder.build().encode().toUri();

        log.info("[KakaoUriBuilderService - buildUriFromKakaoToken] uri: {}", uri);

        return uri;
    }

    public URI buildUriFromKakaoUserInfo() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_REQUEST_USER_INFO_URL);

        URI uri = uriBuilder.build().encode().toUri();

        log.info("[KakaoUriBuilderService - buildUriFromKakaoUserInfo] uri: {}", uri);

        return uri;
    }
}
