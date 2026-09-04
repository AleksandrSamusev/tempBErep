package dev.practice.planner.controller;

import dev.practice.planner.dtos.*;
import dev.practice.planner.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto dto) {
        return new ResponseEntity<>(userService.updateUser(userId, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/devices")
    ResponseEntity<DeviceResponseDto> addDevice(@PathVariable Long userId, @RequestBody @Valid DeviceRequestDto dto) {
        return new ResponseEntity<>(userService.addDevice(userId, dto), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/devices/{deviceId}")
    ResponseEntity<DeviceResponseDto> updateDevice(@PathVariable Long userId, @PathVariable Long deviceId, @Valid @RequestBody DeviceRequestDto dto) {
        return new ResponseEntity<>(userService.updateDevice(userId, deviceId, dto), HttpStatus.OK);
    }

    @PutMapping("/{userId}/availability")
    ResponseEntity<List<AvailabilityDto>> updateAvailability(@PathVariable Long userId,
                                                               @Valid @RequestBody List<AvailabilityDto> availabilityPayload) {
        return new ResponseEntity<List<AvailabilityDto>>(userService.updateUserAvailability(userId, availabilityPayload), HttpStatus.OK);
    }


}
