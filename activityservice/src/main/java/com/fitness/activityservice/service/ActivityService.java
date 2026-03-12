package com.fitness.activityservice.service;

import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;
    ModelMapper modelMapper = new ModelMapper();

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());

        if(!isValidUser) throw new RuntimeException("Invalid user");

        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .activityType(activityRequest.getActivityType())
                .duration(activityRequest.getDuration())
                .calories(activityRequest.getCalories())
                .startTime(activityRequest.getStartTime())
                .additionalDetails(activityRequest.getAdditionalDetails())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        System.out.println(savedActivity);
        System.out.println(modelMapper.map(savedActivity, ActivityResponse.class));
        return modelMapper.map(savedActivity, ActivityResponse.class);

    }

    public ActivityResponse getActivityById(String id) {
        return modelMapper.map(activityRepository.findById(id), ActivityResponse.class);
    }
}
