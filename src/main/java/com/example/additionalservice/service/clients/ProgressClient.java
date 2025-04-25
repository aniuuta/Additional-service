package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.Progress;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProgressClient {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public ProgressClient(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
        this.restTemplate = new RestTemplate();
    }

    // Получение всех прогрессов
    public List<Progress> getAllProgress() {
        Progress[] progresses = restTemplate.getForObject(apiProperties.getBaseUrl() + "/progress", Progress[].class);
        assert progresses != null;
        return Arrays.asList(progresses);
    }
}
