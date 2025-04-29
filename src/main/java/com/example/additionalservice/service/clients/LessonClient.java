package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LessonClient {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public LessonClient(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
    }

    public Lesson[] getAllLessons() {
        return restTemplate.getForObject(apiProperties.getBaseUrl() + "/lessons", Lesson[].class);
    }
}