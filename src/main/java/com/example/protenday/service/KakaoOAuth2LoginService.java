package com.example.protenday.service;

import com.example.protenday.dto.OAuthToken;
import com.example.protenday.dto.response.KakaoLoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoOAuth2LoginService {

    @Value("${kakao.rest-api-key}") private String kakaoRestAPiKey;
    @Value("${kakao.redirect-uri}") private String kakaoRedirectUri;
    @Value("${kakao.client-secret}") private String kakaoClientSecret;

    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;


    public OAuthToken requestAccessToken(String code) {
        URI uri = kakaoUriBuilderService.buildUriFromKakaoToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("grant_type", "authorization_code");
        params.set("client_id", kakaoRestAPiKey);
        params.set("redirect_uri", kakaoRedirectUri);
        params.set("code", code);
        params.set("client_secret", kakaoClientSecret);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        return restTemplate.exchange(uri, HttpMethod.POST, kakaoTokenRequest, OAuthToken.class).getBody();
    }

    public KakaoLoginResponseDto requestUserInfo(String accessToken) {
        URI uri = kakaoUriBuilderService.buildUriFromKakaoUserInfo();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        HttpEntity httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoLoginResponseDto.class).getBody();
    }
}
