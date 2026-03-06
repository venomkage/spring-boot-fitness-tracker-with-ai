package com.fitness.activityservice.dto;

import com.fitness.activityservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType activityType;
    private int duration;
    private int calories;
    private LocalDateTime startTime;
    private Map<String, Object> additionalDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
