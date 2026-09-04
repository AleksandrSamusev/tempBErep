package dev.practice.planner.mapper;

import dev.practice.planner.dtos.AreaPlatformAssignmentRequestDto;
import dev.practice.planner.model.AreaPlatformAssignment;
import dev.practice.planner.model.TestingArea;
import dev.practice.planner.model.User;
import org.springframework.stereotype.Component;

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
}
