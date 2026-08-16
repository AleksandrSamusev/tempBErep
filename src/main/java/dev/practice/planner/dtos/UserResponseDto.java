package dev.practice.planner.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<DeviceResponseDto> devices;
    private List<AvailabilityResponseDto> availabilities;

    private AvailabilityResponseDto currentWeekAvailability;
    private AvailabilityResponseDto nextWeekAvailability;
}
