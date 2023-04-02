package com.example.protenday.controller;

import com.example.protenday.dto.request.ImageRequest;
import com.example.protenday.dto.response.Response;
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

    @GetMapping("/test")
    public Response<Void> test() {
        return Response.success();
    }

    @PostMapping
    public Response<Void> requestSaveImage(@ModelAttribute ImageRequest request) {
        imageService.saveImage(request);

        return Response.success();
    }

    @GetMapping("/{id}")
    public Response<Void> requestGetImage(@PathVariable Long id){
        imageService.searchImage(id);

        return Response.success();
    }

    @PutMapping("/{id}")
    public Response<Void> updateImage(@PathVariable Long id, @ModelAttribute ImageRequest request) {
        imageService.updateImage(id, request);

        return Response.success();
    }
}
