package dev.practice.planner.controller;

import dev.practice.planner.dtos.*;
import dev.practice.planner.service.AvailabilityService;
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
    private final AvailabilityService availabilityService;

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

    @PostMapping("/{userId}/availability")
    ResponseEntity<AvailabilityResponseDto> reportAvailability(@PathVariable Long userId,
                                                               @Valid @RequestBody AvailabilityRequestDto dto) {
        return new ResponseEntity<>(availabilityService.reportAvailability(userId, dto), HttpStatus.CREATED);
    }


}
