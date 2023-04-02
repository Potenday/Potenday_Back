package com.example.protenday.dto.response;

import com.example.protenday.dto.Message;
import com.example.protenday.dto.Plant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private Long id;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private Plant plant;
    private LocalDateTime registeredAt;
    private String registeredBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static MessageResponse fromMessage(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .senderId(message.getSender().getId())
                .senderName(message.getSender().getNickname())
                .receiverId(message.getReceiver().getId())
                .receiverName(message.getReceiver().getNickname())
                .plant(message.getPlant())
                .registeredAt(message.getRegisteredAt())
                .registeredBy(message.getRegisteredBy())
                .modifiedAt(message.getModifiedAt())
                .modifiedBy(message.getModifiedBy())
                .build();
    }
}
