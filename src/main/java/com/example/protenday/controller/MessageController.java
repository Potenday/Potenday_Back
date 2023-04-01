package com.example.protenday.controller;

import com.example.protenday.dto.request.MessageRequest;
import com.example.protenday.service.MessageEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageEntityService messageEntityService;

    @PostMapping
    public void sendMessage(@RequestBody MessageRequest request) {
        messageEntityService.sendMessage(request);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageEntityService.deleteMessage(id);
    }

}
