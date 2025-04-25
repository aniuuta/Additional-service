package com.example.additionalservice.model;

public class UserProgress {

    private Long userId;
    private String fio;
    private double progressPercent;

    public UserProgress() {
    }

    public UserProgress(Long userId, String fio, double progressPercent) {
        this.userId = userId;
        this.fio = fio;
        this.progressPercent = progressPercent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public double getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(double progressPercent) {
        this.progressPercent = progressPercent;
    }
}
