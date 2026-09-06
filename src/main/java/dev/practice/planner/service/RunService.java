package dev.practice.planner.service;

import dev.practice.planner.dtos.*;

import java.util.List;

public interface RunService {
    RunResponseDto createRun(RunRequestDto dto);
    TestingAreaResponseDto createTestingArea(TestingAreaRequestDto dto, Long runId);
    List<RunResponseDto> getAllRuns();
    AreaPlatformAssignmentResponseDto createAssignment(AreaPlatformAssignmentRequestDto dto);
    RunResponseDto getRunById(Long runId);
}
