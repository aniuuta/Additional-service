package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.additionalservice.model.Progress;

import java.util.List;


@Component
public class ProgressClient {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public ProgressClient(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
    }

    public List<Progress> getAllProgress() {
        return restTemplate.exchange(
                apiProperties.getBaseUrl() + "/progress",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Progress>>() {}
        ).getBody();
    }
}
