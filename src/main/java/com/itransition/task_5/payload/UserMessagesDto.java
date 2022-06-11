package com.itransition.task_5.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserMessagesDto {
    private Long id;
    private String senderName;
    private String title;
    private String content;
    private Time sentTime;
}
