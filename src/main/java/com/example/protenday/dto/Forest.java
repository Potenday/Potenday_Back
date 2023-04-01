package com.example.protenday.dto;

import com.example.protenday.domain.ForestEntity;
import com.example.protenday.domain.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Forest {
    private Long id;
    private Set<MessageEntity> messageEntities;

    public static Forest fromEntity(ForestEntity entity) {
        return Forest.builder()
                .id(entity.getId())
                .messageEntities(entity.getMessages())
                .build();
    }
}
