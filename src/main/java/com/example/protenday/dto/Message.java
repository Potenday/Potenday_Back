package com.example.protenday.dto;

import com.example.protenday.domain.MessageEntity;
import com.example.protenday.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;
    private User sender;
    private User receiver;
    private Plant plant;
    private String message;
    private LocalDateTime registeredAt;
    private String registeredBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static Message fromEntity(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .sender(User.fromEntity(entity.getSender()))
                .receiver(User.fromEntity(entity.getReceiver()))
                .plant(Plant.fromEntity(entity.getPlant()))
                .registeredAt(entity.registeredAt)
                .registeredBy(entity.registeredBy)
                .modifiedAt(entity.modifiedAt)
                .modifiedBy(entity.modifiedBy)
                .build();
    }
}
