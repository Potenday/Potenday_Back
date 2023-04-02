package com.example.protenday.controller;

import com.example.protenday.dto.Message;
import com.example.protenday.dto.request.MessageRequest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.MessageEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageEntityService messageEntityService;

    @PostMapping
    public Response<Message> sendMessage(@RequestBody MessageRequest request, Authentication authentication) {
        Message message = messageEntityService.sendMessage(request, authentication.getName());

        return Response.success(message);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteMessage(@PathVariable Long id) {
        messageEntityService.deleteMessage(id);

        return Response.success();
    }

}
