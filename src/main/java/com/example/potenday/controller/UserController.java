package com.example.potenday.controller;

import com.example.potenday.dto.request.UserRequest;
import com.example.potenday.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserEntityService userEntityService;

    @PostMapping("/sign-up")
    public void registerUser(@RequestBody UserRequest request) {
        userEntityService.registerUser(request);
    }

    @GetMapping("/login")
    public void login(@RequestParam("code") String code) {
        userEntityService.login(code);
    }

}
