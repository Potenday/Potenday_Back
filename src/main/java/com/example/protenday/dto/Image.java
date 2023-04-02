package com.example.protenday.dto;

import com.example.protenday.domain.ImageEntity;
import com.example.protenday.util.ImageUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private Long id;
    private String filaname;
    private byte[] imageData;

    public static Image fromEntity(ImageEntity entity) {
        return Image.builder()
                .id(entity.getId())
                .filaname(entity.getFileName())
                .imageData(entity.getImageData())
                .build();
    }

    public void decompressImage() {
        this.imageData = ImageUtils.decompressImage(this.imageData);
    }
}
