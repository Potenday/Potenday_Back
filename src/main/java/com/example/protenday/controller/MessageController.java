package com.example.protenday.controller;

import com.example.protenday.dto.Message;
import com.example.protenday.dto.request.MessageRequest;
import com.example.protenday.dto.response.MessageResponse;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.MessageEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public Response<List<MessageResponse>> searchMessageList() {
        List<Message> messages = messageEntityService.searchMessageList();

        return Response.success(messages.stream().map(MessageResponse::fromMessage).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Response<MessageResponse> searchMessage(@PathVariable Long id) {
        Message message = messageEntityService.searchMessage(id);

        return Response.success(MessageResponse.fromMessage(message));
    }

}
