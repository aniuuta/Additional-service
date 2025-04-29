package com.example.additionalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProgress {
    private int userId;
    private String userFio;
    private double averageProgress;
}
