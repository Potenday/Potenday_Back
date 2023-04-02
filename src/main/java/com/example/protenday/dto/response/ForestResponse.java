package com.example.protenday.dto.response;

import com.example.protenday.dto.Forest;
import com.example.protenday.dto.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForestResponse {

    private Long forestId;
    private Set<MessageResponse> messages;

    public static ForestResponse fromForest(Forest forest) {
        return ForestResponse.builder()
                .forestId(forest.getId())
                .messages(forest.getMessageEntities().stream()
                        .map(Message::fromEntity)
                        .map(MessageResponse::fromMessage)
                        .collect(Collectors.toUnmodifiableSet())
                )
                .build();
    }
}
