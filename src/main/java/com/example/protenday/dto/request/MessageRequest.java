package com.example.protenday.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    /** Receiver */
    private Long forestId;

    /** 전달할 나무 */
    private Long treeId;
}
