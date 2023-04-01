package com.example.potenday.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    @PostMapping
    public void requestSaveImage() {

    }

    @GetMapping("/{id}")
    public void requestGetImage(@PathVariable Long id){

    }
}
