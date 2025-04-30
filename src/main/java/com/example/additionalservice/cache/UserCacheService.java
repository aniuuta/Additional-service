package com.example.additionalservice.cache;

import com.example.additionalservice.model.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserCacheService {

    private final Map<Integer, User> userCache = new HashMap<>();

    public User getUserFromCache(Integer id) {
        return userCache.get(id);
    }

    public void putUserInCache(Integer id, User user) {
        userCache.put(id, user);
    }

    public boolean contains(Integer id) {
        return userCache.containsKey(id);
    }

    public User getFromCache(Integer userId) {
        return userCache.get(userId);
    }

    public void putToCache(Integer userId, User user) {
        userCache.put(userId, user);
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void printCacheStats() {
        System.out.println("User cache size: " + userCache.size());
    }
}
