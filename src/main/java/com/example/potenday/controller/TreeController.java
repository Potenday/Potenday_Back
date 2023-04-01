package com.example.potenday.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trees")
public class TreeController {

    @PostMapping
    public void requestSendTree() {
        // 1. TreeId, 보낸 사람 통해 Tree 저장 +
    }

    @GetMapping
    public void requestTreeList() {

    }

    @GetMapping("/{id}")
    public void requestTree(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void requestRemoveTree(@PathVariable Long id) {

    }
}
