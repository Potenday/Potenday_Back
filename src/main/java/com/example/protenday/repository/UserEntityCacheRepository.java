package com.example.protenday.repository;

import com.example.protenday.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserEntityCacheRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    public final static Duration CACHE_TTL = Duration.ofDays(7);

    private ValueOperations<String, Object> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    public void setUser(User user){
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        this.valueOperations = redisTemplate.opsForValue();

        if(Objects.isNull(user) || Objects.isNull(user.getEmail())){
            log.error("Required Values must not be null");
            return;
        }

        try{
            String key = getKey(user.getEmail());
            valueOperations.set(key, user, CACHE_TTL);
            log.info("[UserCacheRepository save - success]  id: {}", user.getId());
        }catch (Exception e){
            log.error("[UserCacheRepository save - error] {}", e.getMessage());
        }
    }

    public Optional<User> getUser(String email){
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        this.valueOperations = redisTemplate.opsForValue();

        String key = getKey(email);
        User user = (User) valueOperations.get(key);
        log.info("Get data from Redis {}, {}", key, user);
        return Optional.ofNullable(user);
    }

    public void setRefreshToken(String email, String refreshToken) {
        String key = getRefreshTokenKey(email);
        valueOperations.set(key, refreshToken, CACHE_TTL);
    }

    public String getRefreshToken(String email) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.valueOperations = redisTemplate.opsForValue();
        String key = getRefreshTokenKey(email);
        return valueOperations.get(key).toString();
    }

    public String getKey(String email){
        return "USER:"+email;
    }

    public String getRefreshTokenKey(String email) {
        return "RTK:"+email;
    }

    public void deleteRefreshToken(String email) {
        redisTemplate.delete(getKey(email));
        redisTemplate.delete(getRefreshTokenKey(email));
    }
}
