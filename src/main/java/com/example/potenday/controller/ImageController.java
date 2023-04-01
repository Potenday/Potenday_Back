package com.example.potenday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/images")
@Controller
public class ImageController {

    @PostMapping
    public void requestSaveImage() {

    }

    @GetMapping("/{id}")
    public void requestGetImage(@PathVariable Long id){

    }
}
