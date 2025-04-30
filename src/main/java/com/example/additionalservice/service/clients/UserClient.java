package com.example.additionalservice.service.clients;

import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public UserClient(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
    }

    public User[] getAllUsers() {
        String url = apiProperties.getBaseUrl() + "/users";
        logger.info("Sending request to fetch all users: {}", url);
        return restTemplate.getForObject(url, User[].class);
    }

    public User getUserById(Integer userId) {
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
