package com.example.additionalservice.service.clients;


import com.example.additionalservice.ApiProperties;
import com.example.additionalservice.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ApiProperties apiProperties;

    public UserClient(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public User[] getAllUsers() {
        return restTemplate.getForObject(apiProperties.getBaseUrl() + "/users", User[].class);
    }

    public User getUserById(Long userId) {
        return restTemplate.getForObject(apiProperties.getBaseUrl() + "/users/" + userId, User.class);
    }
}
