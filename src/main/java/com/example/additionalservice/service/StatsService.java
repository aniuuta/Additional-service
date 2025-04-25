package com.example.additionalservice.service;

import com.example.additionalservice.model.Lesson;
import com.example.additionalservice.model.Progress;
import com.example.additionalservice.model.User;
import com.example.additionalservice.model.UserProgress;
import com.example.additionalservice.service.clients.LessonClient;
import com.example.additionalservice.service.clients.ProgressClient;
import com.example.additionalservice.service.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final UserClient userClient;
    private final LessonClient lessonClient;
    private final ProgressClient progressClient;

    @Autowired
    public StatsService(UserClient userClient, LessonClient lessonClient, ProgressClient progressClient) {
        this.userClient = userClient;
        this.lessonClient = lessonClient;
        this.progressClient = progressClient;
    }

    public List<UserProgress> calculateOverallProgress() {
        // Получаем всех пользователей, уроки и прогрессы
        List<User> users = List.of(userClient.getAllUsers());
        List<Lesson> lessons = List.of(lessonClient.getAllLessons());
        List<Progress> progresses = progressClient.getAllProgress();

        // Индексируем прогрессы по userId и lessonId для более быстрой фильтрации
        Map<Long, Map<Long, Progress>> userLessonProgressMap = progresses.stream()
                .collect(Collectors.groupingBy(
                        progress -> progress.getUser().getId(),
                        Collectors.toMap(progress -> progress.getLesson().getId(), progress -> progress)
                ));

        // Считываем прогресс для каждого пользователя
        return users.stream().map(user -> {
            // Получаем прогресс для этого пользователя по урокам
            Map<Long, Progress> userProgressMap = userLessonProgressMap.getOrDefault(user.getId(), Collections.emptyMap());

            double totalProgress = 0;

            // Перебираем все уроки
            for (Lesson lesson : lessons) {
                // Получаем прогресс по конкретному уроку
                Progress progress = userProgressMap.get(lesson.getId());

                if (progress != null) {
                    // Если прогресс существует, проверяем результат теста
                    totalProgress += (progress.getTestResult() >= 50) ? 100.0 : 0.0;
                }
            }

            // Рассчитываем средний прогресс
            double averageProgress = lessons.isEmpty() ? 0.0 : totalProgress / lessons.size();

            // Возвращаем результат для пользователя
            return new UserProgress(user.getId(), user.getFio(), averageProgress);
        }).collect(Collectors.toList());
    }
}
