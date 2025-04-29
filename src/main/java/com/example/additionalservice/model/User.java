package com.example.additionalservice.model;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String fio;
    private String login;
    private String email;
    private LocalDateTime dateRegistry;
}