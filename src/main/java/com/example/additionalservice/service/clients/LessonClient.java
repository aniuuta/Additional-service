package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.Lesson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LessonClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ApiProperties apiProperties;

    public LessonClient(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public Lesson[] getAllLessons() {
        return restTemplate.getForObject(apiProperties.getBaseUrl() + "/lessons", Lesson[].class);
    }

    public Lesson getLessonById(Long lessonId) {
        return restTemplate.getForObject(apiProperties.getBaseUrl() + "/lessons/" + lessonId, Lesson.class);
    }
}
