package dev.practice.planner.mapper;

import dev.practice.planner.dtos.TestingAreaRequestDto;
import dev.practice.planner.dtos.TestingAreaResponseDto;
import dev.practice.planner.model.Run;
import dev.practice.planner.model.TestingArea;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TestingAreaMapper {

    private final AreaPlatformAssignmentMapper areaPlatformAssignmentMapper;

    public TestingArea toTestingArea(TestingAreaRequestDto dto, Run run) {
        TestingArea testingArea = new TestingArea();

        testingArea.setRun(run);
        testingArea.setNotes(dto.getNotes());
        testingArea.setTag(dto.getTag());
        testingArea.setUrl(dto.getUrl());
        testingArea.setComplexity(dto.getComplexity());
        testingArea.setLocation(dto.getLocation());
        return testingArea;
    }

    public TestingAreaResponseDto toTestingAreaResponseDto(TestingArea testingArea) {
        if (testingArea == null) {
            return null;
        }
        TestingAreaResponseDto dto = new TestingAreaResponseDto();
        dto.setId(testingArea.getId());
        dto.setTag(testingArea.getTag());
        dto.setLocation(testingArea.getLocation());
        dto.setUrl(testingArea.getUrl());
        dto.setComplexity(testingArea.getComplexity());
        dto.setNotes(testingArea.getNotes());

        if (testingArea.getRun() != null) {
            dto.setRunId(testingArea.getRun().getId());
        }
        if (testingArea.getAssignments() != null) {
            dto.setAssignments(areaPlatformAssignmentMapper.toAreaPlatformAssignmentResponseDtos(
                    testingArea.getAssignments()));
        }
        return dto;
    }

    public List<TestingAreaResponseDto> toTestingAreaResponseDtos(List<TestingArea> testingAreas) {
        if(testingAreas == null || testingAreas.isEmpty()) {
            return new ArrayList<>();
        }
        return testingAreas.stream().map(this::toTestingAreaResponseDto).toList();
    }
}
