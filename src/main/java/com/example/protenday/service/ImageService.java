package com.example.protenday.service;

import com.example.protenday.domain.ImageEntity;
import com.example.protenday.dto.Image;
import com.example.protenday.dto.request.ImageRequest;
import com.example.protenday.repository.cache.ImageCacheRepository;
import com.example.protenday.repository.ImageEntityRepository;
import com.example.protenday.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageEntityRepository imageEntityRepository;
    private final ImageCacheRepository imageCacheRepository;

    public void saveImage(ImageRequest request) {
        try {
            ImageEntity imageEntity = imageEntityRepository.save(ImageEntity.builder()
                    .fileName(request.getImage().getOriginalFilename())
                    .imageData(ImageUtils.compressImage(request.getImage().getBytes()))
                    .build());

            imageCacheRepository.saveImage(Image.fromEntity(imageEntity));
            log.info("[ImageService save - success]");
        }catch (IOException e) {
            log.error("[ImageService save - error] {}", e.getMessage());
        }
    }

    public Image searchImage(Long id) {
        Image image = imageCacheRepository.searchImage(id);
        if(Objects.isNull(image) || Objects.isNull(image.getId())) {
            ImageEntity imageEntity = imageEntityRepository.getReferenceById(id);
            image = Image.fromEntity(imageEntity);
        }

        image.decompressImage();

        return image;
    }

    public Image updateImage(Long id, ImageRequest request) {
        try {
            ImageEntity imageEntity = imageEntityRepository.findById(id).get();

            imageEntity.updateImageSource(ImageUtils.compressImage(request.getImage().getBytes()));

            return Image.fromEntity(imageEntity);
        }catch (IOException e) {
            return null;
        }
    }
}
