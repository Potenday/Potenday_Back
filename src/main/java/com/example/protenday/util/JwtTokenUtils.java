package com.example.protenday.util;

import com.example.protenday.dto.TokenInfo;
import com.example.protenday.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtils {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;              // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일

    public static String getEmail(String token, String key){
        return extractClaims(token, key).get("email", String.class);
    }

    public static boolean isExpired(String token, String key) {
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    private static Claims extractClaims(String token, String key){
        return Jwts.parserBuilder().setSigningKey(getKey(key))
                .build().parseClaimsJws(token).getBody();
    }

    private static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public static TokenInfo createTokenInfo(User user, String key) {
        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());
        long now = (new Date()).getTime();

        String accessToken = createAccessToken(user.getEmail(), claims, now, key);
        String refreshToken = createRefreshToken(now, key);

        return TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }

    private static String createRefreshToken(long now, String key) {
        return Jwts.builder()
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(getKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    private static String createAccessToken(String username, Claims claims, long now, String key) {
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(getKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    public static TokenInfo reIssue(User user, String key, String refreshToken) {
        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());
        long now = (new Date()).getTime();

        String accessToken = createAccessToken(user.getEmail(), claims, now, key);

        return TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

