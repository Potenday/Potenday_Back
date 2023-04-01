package com.example.potenday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/trees")
@Controller
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

    @DeleteMapping("/{id")
    public void requestRemoveTree(@PathVariable Long id) {

    }
}
