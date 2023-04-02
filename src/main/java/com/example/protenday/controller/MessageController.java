package com.example.protenday.controller;

import com.example.protenday.dto.request.MessageRequest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.MessageEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageEntityService messageEntityService;

    @GetMapping("/test")
    public Response<Void> test() {
        return Response.success();
    }

    @PostMapping
    public Response<Void> sendMessage(@RequestBody MessageRequest request) {
        messageEntityService.sendMessage(request);

        return Response.success();
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteMessage(@PathVariable Long id) {
        messageEntityService.deleteMessage(id);

        return Response.success();
    }

}
