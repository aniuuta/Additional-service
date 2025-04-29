package com.example.additionalservice.service;

import com.example.additionalservice.cache.UserCacheService;
import com.example.additionalservice.model.*;
import com.example.additionalservice.service.clients.LessonClient;
import com.example.additionalservice.service.clients.ProgressClient;
import com.example.additionalservice.service.clients.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsService {

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);

    private final UserClient userClient;
    private final LessonClient lessonClient;
    private final ProgressClient progressClient;
    private final UserCacheService userCacheService;

    public StatsService(UserClient userClient,
                        LessonClient lessonClient,
                        ProgressClient progressClient,
                        UserCacheService userCacheService) {
        this.userClient = userClient;
        this.lessonClient = lessonClient;
        this.progressClient = progressClient;
        this.userCacheService = userCacheService;
    }

    public List<UserProgress> calculateOverallProgress() {
        List<Lesson> lessons = List.of(lessonClient.getAllLessons());
        List<Progress> progresses = progressClient.getAllProgress();

        Map<Integer, List<Progress>> userProgressMap = new HashMap<>();
        for (Progress progress : progresses) {
            userProgressMap.computeIfAbsent(progress.getUser(), k -> new ArrayList<>()).add(progress);
        }

        List<UserProgress> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Progress>> entry : userProgressMap.entrySet()) {
            Integer userId = entry.getKey();
            List<Progress> userProgresses = entry.getValue();

            // Используем кэш
            User user;
            if (userCacheService.contains(userId)) {
                user = userCacheService.getUserFromCache(userId);
            } else {
                user = userClient.getUserById(userId);
                if (user != null) {
                    userCacheService.putUserInCache(userId, user);
                }
            }

            if (user == null) {
                logger.warn("No user data found for userId: {}", userId);
                continue;
            }

            Map<Integer, Progress> progressMap = new HashMap<>();
            for (Progress p : userProgresses) {
                progressMap.put(p.getLesson(), p);
            }

            double total = 0.0;

            for (Lesson lesson : lessons) {
                Progress p = progressMap.get(lesson.getId());
                if (p != null && p.getTestResult() >= 50) {
                    total += 100.0;
                }
            }

            double average = lessons.isEmpty() ? 0.0 : total / lessons.size();

            if (average > 0) {
                result.add(new UserProgress(user.getId(), user.getFio(), average));
            }
        }

        return result;
    }
}
