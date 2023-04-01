package com.example.potenday.controller;

import com.example.potenday.dto.request.UserRequest;
import com.example.potenday.service.UserEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
@Controller
public class UserController {

    private UserEntityService userEntityService;

    @PostMapping("/sign-up")
    public void registerUser(@RequestBody UserRequest request) {
        userEntityService.registerUser(request);
    }
}
