package dev.practice.planner.service.impl;

import dev.practice.planner.dtos.AvailabilityRequestDto;
import dev.practice.planner.dtos.AvailabilityResponseDto;
import dev.practice.planner.model.Availability;
import dev.practice.planner.model.User;
import dev.practice.planner.repository.AvailabilityRepository;
import dev.practice.planner.repository.UserRepository;
import dev.practice.planner.service.AvailabilityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public AvailabilityResponseDto reportAvailability(Long userId, AvailabilityRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with given id: " + userId));
        if (!availabilityRepository.existsByUserIdAndWeekStartDate(userId, dto.getWeekStartDate())) {
            Availability availability = new Availability();
            availability.setWeekStartDate(dto.getWeekStartDate());
            availability.setMonThuHours(dto.getMonThuHours());
            availability.setFriSunHours(dto.getFriSunHours());
            availability.setUser(user);
            Availability savedAvailability = availabilityRepository.save(availability);
            user.getAvailabilities().add(savedAvailability);
            userRepository.save(user);
            return toAvailabilityResponse(savedAvailability);
        } else {
            throw new InvalidParameterException("Availability is already reported for week: " + dto.getWeekStartDate());
        }
    }

    private AvailabilityResponseDto toAvailabilityResponse(Availability availability) {
        return new AvailabilityResponseDto(
                availability.getId(),
                availability.getWeekStartDate(),
                availability.getMonThuHours(),
                availability.getFriSunHours()
        );
    }
}
