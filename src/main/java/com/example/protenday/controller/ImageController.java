package com.example.protenday.controller;

import com.example.protenday.dto.request.ImageRequest;
import com.example.protenday.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public void requestSaveImage(@ModelAttribute ImageRequest request) {
        imageService.saveImage(request);
    }

    @GetMapping("/{id}")
    public void requestGetImage(@PathVariable Long id){
        imageService.searchImage(id);
    }

    @PutMapping("/{id}")
    public void updateImage(@PathVariable Long id, @ModelAttribute ImageRequest request) {
        imageService.updateImage(id, request);
    }
}
