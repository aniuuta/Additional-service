package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.Lesson;
import com.example.additionalservice.service.statistics.ObservabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LessonClient {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;
    private final ObservabilityService observabilityService;

    public LessonClient(RestTemplate restTemplate, ApiProperties apiProperties, ObservabilityService observabilityService) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
        this.observabilityService = observabilityService;
    }

    public Lesson[] getAllLessons() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllLessons");
        Lesson[] temp = restTemplate.getForObject(apiProperties.getBaseUrl() + "/lessons", Lesson[].class);
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllLessons");
        return temp;
    }
}