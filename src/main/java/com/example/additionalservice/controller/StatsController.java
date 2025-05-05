package com.example.additionalservice.controller;

import com.example.additionalservice.model.UserProgress;
import com.example.additionalservice.service.StatsService;
import com.example.additionalservice.service.statistics.ObservabilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final ObservabilityService observabilityService;
    public StatsController(StatsService statsService, ObservabilityService observabilityService) {
        this.statsService = statsService;
        this.observabilityService = observabilityService;
    }

    @GetMapping("/overall")
    public List<UserProgress> getOverallProgress() {
        this.observabilityService.start(getClass().getSimpleName() + ":getOverallProgress");
        List<UserProgress> temp = statsService.calculateOverallProgress();
        this.observabilityService.stop(getClass().getSimpleName() + ":getOverallProgress");
        return temp;

    }
}
