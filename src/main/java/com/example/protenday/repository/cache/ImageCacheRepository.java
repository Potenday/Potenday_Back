package com.example.protenday.repository.cache;

import com.example.protenday.dto.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ImageCacheRepository {

    private static final String CACHE_KEY = "IMAGE";

    private final RedisTemplate<String, Object> redisTemplate;
    private ValueOperations<String, Object> valueOperations;

    @PostConstruct
    public void init() {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Image>(Image.class));
    }

    public void saveImage(Image image) {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Image>(Image.class));
        this.valueOperations = redisTemplate.opsForValue();

        if(Objects.isNull(image) || Objects.isNull(image.getId())) {
            log.error("Required subKey or value must not be null");
            return;
        }

        try {
            this.valueOperations.set(CACHE_KEY + image.getId(), image.getImageData());

            log.info("[ImageCacheRepository save - success]");
        }catch (Exception e) {
            log.error("[ImageCacheRepository save - error] {}", e.getMessage());
        }
    }

    public byte[] searchImage(Long id) {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Image>(Image.class));
        this.valueOperations = redisTemplate.opsForValue();

        try {
            byte[] image = (byte[]) this.valueOperations.get(CACHE_KEY + id);

            log.info("[ImageCacheRepository searchImage - success]");

            return image;

        } catch (Exception e) {
            log.error("[ImageCacheRepository searchImage - error] {}", e.getMessage());
            return null;
        }
    }
}
