package com.example.protenday.controller;

import com.example.protenday.dto.request.UserRequest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserEntityService userEntityService;

    @GetMapping("/test")
    public Response<Void> test() {
        return Response.success();
    }

    @PostMapping("/sign-up")
    public Response<Void> registerUser(@RequestBody UserRequest request) {
        userEntityService.registerUser(request);

        return Response.success();
    }

    @GetMapping("/login")
    public Response<Void> login(@RequestParam("code") String code) {
        userEntityService.login(code);

        return Response.success();
    }

}
