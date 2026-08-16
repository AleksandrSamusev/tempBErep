package dev.practice.planner.service;

import dev.practice.planner.dtos.DeviceRequestDto;
import dev.practice.planner.dtos.DeviceResponseDto;
import dev.practice.planner.dtos.UserRequestDto;
import dev.practice.planner.dtos.UserResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto request);

    List<UserResponseDto> getAllUsers();

    void deleteUser(Long userId);

    DeviceResponseDto addDevice(Long userId, DeviceRequestDto dto);

    DeviceResponseDto updateDevice(Long userId, Long deviceId, DeviceRequestDto dto);

    UserResponseDto updateUser(Long userId, UserRequestDto dto);
}
