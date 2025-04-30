package com.example.additionalservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Progress {
    private int user;
    private int lesson;
    private LocalDateTime ending;
    private int testResult;
}
