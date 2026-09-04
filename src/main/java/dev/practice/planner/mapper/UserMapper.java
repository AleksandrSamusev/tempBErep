package dev.practice.planner.mapper;

import dev.practice.planner.dtos.AvailabilityDto;
import dev.practice.planner.dtos.DeviceResponseDto;
import dev.practice.planner.dtos.UserResponseDto;
import dev.practice.planner.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserMapper {

    private final DeviceMapper deviceMapper;
    private final AvailabilityMapper availabilityMapper;

    public UserResponseDto toUserResponseDto(User user) {
        if(user == null) {
            return null;
        }
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setDevices(deviceMapper.toDeviceResponseDtos(user.getDevices()));
        responseDto.setAvailabilities(availabilityMapper.toAvailabilityDtos(user.getAvailabilities()));
        responseDto.setEmail(user.getEmail());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        return responseDto;
    }
}
