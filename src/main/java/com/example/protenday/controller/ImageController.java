package com.example.protenday.controller;

import com.example.protenday.dto.Image;
import com.example.protenday.dto.request.ImageRequest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public Response<Void> requestSaveImage(@ModelAttribute MultipartFile image) {
        imageService.saveImage(image);

        return Response.success();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> requestGetImage(@PathVariable Long id){
        byte[] source = imageService.searchImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(source);
    }

    @PutMapping("/{id}")
    public Response<Void> updateImage(@PathVariable Long id, @ModelAttribute ImageRequest request) {
        imageService.updateImage(id, request);

        return Response.success();
    }
}
