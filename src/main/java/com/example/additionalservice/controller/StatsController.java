package com.example.additionalservice.controller;

import com.example.additionalservice.model.Lesson;
import com.example.additionalservice.model.Progress;
import com.example.additionalservice.model.User;
import com.example.additionalservice.model.UserProgress;
import com.example.additionalservice.service.StatsService;
import com.example.additionalservice.service.clients.LessonClient;
import com.example.additionalservice.service.clients.ProgressClient;
import com.example.additionalservice.service.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final ProgressClient progressClient;
    private final LessonClient lessonClient;
    private final UserClient userClient;


    @Autowired
    public StatsController(StatsService statsService, LessonClient lessonClient, UserClient userClient, ProgressClient progressClient) {
        this.statsService = statsService;
        this.lessonClient = lessonClient;
        this.userClient = userClient;
        this.progressClient = progressClient;
    }

    @GetMapping("/")
    public List<UserProgress> getStatsByArtist() {
        return statsService.calculateOverallProgress();

    }

    @GetMapping("/getAllLessons")
    public List<Lesson> getAllLessons() {
        // Получаем все уроки через LessonClient
        return List.of(lessonClient.getAllLessons());
    }
    @GetMapping("/getAllProgress")
    public List<Progress> getAllProgress() {
        // Получаем все записи прогресса через ProgressClient
        return List.of((Progress) progressClient.getAllProgress());
    }
    @GetMapping("/overall-progress")
    public List<UserProgress> getOverallProgress() {
        // Запрашиваем и рассчитываем общий прогресс
        return statsService.calculateOverallProgress();
    }
}
