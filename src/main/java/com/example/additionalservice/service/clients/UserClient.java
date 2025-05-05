package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.User;
import com.example.additionalservice.service.statistics.ObservabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;
    private final ObservabilityService observabilityService;

    public UserClient(RestTemplate restTemplate, ApiProperties apiProperties, ObservabilityService observabilityService) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
        this.observabilityService = observabilityService;
    }

    public User[] getAllUsers() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllProgress");
        String url = apiProperties.getBaseUrl() + "/users";
        logger.info("Sending request to fetch all users: {}", url);
        return restTemplate.getForObject(url, User[].class);

    }

    public User getUserById(Integer userId) {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllProgress");
        String url = apiProperties.getBaseUrl() + "/users/" + userId;
        logger.info("Sending request to get user with ID: {}", userId);
        User user = restTemplate.getForObject(url, User.class);
        if (user != null) {
            logger.info("Received user data: {}", user);
        } else {
            logger.warn("No data received for user ID: {}", userId);
        }

        return user;
    }
}
