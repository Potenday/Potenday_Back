package com.example.protenday.service;

import com.example.protenday.domain.ForestEntity;
import com.example.protenday.domain.MessageEntity;
import com.example.protenday.domain.UserEntity;
import com.example.protenday.dto.Message;
import com.example.protenday.dto.request.MessageRequest;
import com.example.protenday.repository.ForestEntityRepository;
import com.example.protenday.repository.MessageEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageEntityService {

    private final MessageEntityRepository messageEntityRepository;
    private final ForestEntityRepository forestEntityRepository;

    public Message sendMessage(MessageRequest request) {
        // 1. 보낸사람 정보 찾기(토큰을 통해)
        UserEntity sender = null;

        // 2. 받는 사람
        ForestEntity forestEntity = forestEntityRepository.findById(request.getForestId()).get();
        UserEntity receiver = forestEntity.getUserEntity();

        MessageEntity messageEntity = messageEntityRepository.save(MessageEntity.builder()
                .receiver(receiver)
                .forestEntity(forestEntity)
                .build());

        return Message.fromEntity(messageEntity);
    }

    public void deleteMessage(Long id) {
        messageEntityRepository.deleteById(id);
    }
}
