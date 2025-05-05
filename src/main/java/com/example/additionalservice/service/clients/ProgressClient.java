package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.service.statistics.ObservabilityService;
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
    private final ObservabilityService observabilityService;

    public ProgressClient(RestTemplate restTemplate, ApiProperties apiProperties, ObservabilityService observabilityService) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
        this.observabilityService = observabilityService;
    }

    public List<Progress> getAllProgress() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllProgress");
        List<Progress> temp = restTemplate.exchange(
                apiProperties.getBaseUrl() + "/progress",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Progress>>() {}

        ).getBody();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllProgress");
        return temp;
    }
}
