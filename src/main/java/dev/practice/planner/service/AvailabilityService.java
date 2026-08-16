package dev.practice.planner.service;

import dev.practice.planner.dtos.AvailabilityRequestDto;
import dev.practice.planner.dtos.AvailabilityResponseDto;

public interface AvailabilityService {
    AvailabilityResponseDto reportAvailability(Long userId, AvailabilityRequestDto dto);
}
