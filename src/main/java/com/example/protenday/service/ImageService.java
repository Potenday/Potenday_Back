package com.example.protenday.service;

import com.example.protenday.domain.ImageEntity;
import com.example.protenday.dto.Image;
import com.example.protenday.dto.request.ImageRequest;
import com.example.protenday.repository.ImageEntityRepository;
import com.example.protenday.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageEntityRepository imageEntityRepository;

    public void saveImage(ImageRequest request) {
        try {
            imageEntityRepository.save(ImageEntity.builder()
                    .fileName(request.getImage().getOriginalFilename())
                    .imageData(ImageUtils.compressImage(request.getImage().getBytes()))
                    .build());
            // TODO: 캐시에도 저장

        }catch (IOException e) {

        }
    }

    public Image getImage(Long id) {
        // TODO: 캐시를 통해 변경할 것
        ImageEntity imageEntity = imageEntityRepository.getReferenceById(id);
        imageEntity.decompressImage();

        return Image.fromEntity(imageEntity);
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
