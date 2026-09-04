package dev.practice.planner.mapper;

import dev.practice.planner.dtos.AreaPlatformAssignmentRequestDto;
import dev.practice.planner.dtos.AreaPlatformAssignmentResponseDto;
import dev.practice.planner.model.AreaPlatformAssignment;
import dev.practice.planner.model.TestingArea;
import dev.practice.planner.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AreaPlatformAssignmentMapper {
    public AreaPlatformAssignment toAreaPlatformAssignment(AreaPlatformAssignmentRequestDto dto,
                                                           User user,
                                                           TestingArea testingArea) {
        AreaPlatformAssignment assignment = new AreaPlatformAssignment();
        assignment.setPlatform(dto.getPlatform());
        assignment.setTestingArea(testingArea);
        assignment.setTester(user);
        return assignment;
    }

    public AreaPlatformAssignmentResponseDto toAreaPlatformAssignmentResponseDto(AreaPlatformAssignment assignment) {
        if (assignment == null) {
            return null;
        }
        AreaPlatformAssignmentResponseDto dto = new AreaPlatformAssignmentResponseDto();
        dto.setId(assignment.getId());
        dto.setPlatform(assignment.getPlatform());
        if (assignment.getTestingArea() != null) {
            dto.setTestingAreaId(assignment.getTestingArea().getId());
        }
        if (assignment.getTester() != null) {
            dto.setTesterId(assignment.getTester().getId());
            dto.setTesterName(assignment.getTester().getFirstName() + " " +
                    assignment.getTester().getLastName());
        }
        return dto;
    }

    public List<AreaPlatformAssignmentResponseDto> toAreaPlatformAssignmentResponseDtos(
            List<AreaPlatformAssignment> assignments) {
        if(assignments == null || assignments.isEmpty()) {
            return new ArrayList<>();
        }
        return assignments.stream().map(this::toAreaPlatformAssignmentResponseDto).toList();
    }
}
