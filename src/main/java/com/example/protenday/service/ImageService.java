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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageEntityRepository imageEntityRepository;
    private final ImageCacheRepository imageCacheRepository;

    public void saveImage(MultipartFile image) {
        try {
            ImageEntity imageEntity = imageEntityRepository.save(ImageEntity.builder()
                    .fileName(image.getOriginalFilename())
                    .imageData(ImageUtils.compressImage(image.getBytes()))
                    .build());

            imageCacheRepository.saveImage(Image.fromEntity(imageEntity));
            log.info("[ImageService save - success]");
        }catch (IOException e) {
            log.error("[ImageService save - error] {}", e.getMessage());
        }
    }

    public  byte[] searchImage(Long id) {
        byte[] source = imageCacheRepository.searchImage(id);

        if(Objects.isNull(source)) {
            ImageEntity imageEntity = imageEntityRepository.findById(id).get();
            imageCacheRepository.saveImage(Image.fromEntity(imageEntity));
            return ImageUtils.decompressImage(imageEntity.getImageData());
        }else{
            return ImageUtils.decompressImage(source);
        }
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
