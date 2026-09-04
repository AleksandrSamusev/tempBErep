package dev.practice.planner.mapper;

import dev.practice.planner.dtos.AvailabilityDto;
import dev.practice.planner.model.Availability;
import dev.practice.planner.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AvailabilityMapper {

    public Availability toAvailability(AvailabilityDto dto, User user) {
        if(dto == null) {
            return null;
        }
        Availability availability = new Availability();
        availability.setDate(dto.getDate());
        availability.setStatus(dto.getStatus());
        availability.setUser(user);
        return availability;
    }

    public AvailabilityDto toAvailabilityDto(Availability availability) {
        if(availability == null) {
            return null;
        }
        AvailabilityDto dto = new AvailabilityDto();
        dto.setDate(availability.getDate());
        dto.setStatus(availability.getStatus());
        return dto;
    }

    public List<AvailabilityDto> toAvailabilityDtos(List<Availability> availabilities) {
        if(availabilities == null || availabilities.isEmpty()) {
            return new ArrayList<>();
        }
        return availabilities.stream().map(this::toAvailabilityDto).toList();
    }

}
