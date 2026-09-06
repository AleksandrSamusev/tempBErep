package dev.practice.planner.service.impl;

import dev.practice.planner.dtos.*;
import dev.practice.planner.mapper.AreaPlatformAssignmentMapper;
import dev.practice.planner.mapper.RunMapper;
import dev.practice.planner.mapper.TestingAreaMapper;
import dev.practice.planner.model.AreaPlatformAssignment;
import dev.practice.planner.model.Run;
import dev.practice.planner.model.TestingArea;
import dev.practice.planner.model.User;
import dev.practice.planner.repository.AreaPlatformAssignmentRepository;
import dev.practice.planner.repository.RunRepository;
import dev.practice.planner.repository.TestingAreaRepository;
import dev.practice.planner.repository.UserRepository;
import dev.practice.planner.service.RunService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RunServiceImpl implements RunService {
    private final RunRepository runRepository;
    private final TestingAreaRepository testingAreaRepository;
    private final UserRepository userRepository;
    private final AreaPlatformAssignmentRepository areaPlatformAssignmentRepository;
    private final RunMapper runMapper;
    private final TestingAreaMapper testingAreaMapper;
    private final AreaPlatformAssignmentMapper areaPlatformAssignmentMapper;


    @Override
    @Transactional
    public RunResponseDto createRun(RunRequestDto dto) {
        Run run = runMapper.toRun(dto);
        Run savedRun = runRepository.save(run);
        return runMapper.toRunResponseDto(savedRun);
    }

    @Override
    @Transactional
    public TestingAreaResponseDto createTestingArea(TestingAreaRequestDto dto, Long runId) {
        Run run = runRepository.findById(runId).orElseThrow(
                () -> new EntityNotFoundException("Run not found with given id: " + runId));
        TestingArea testingArea = testingAreaMapper.toTestingArea(dto, run);
        TestingArea savedTestingArea = testingAreaRepository.save(testingArea);
        return testingAreaMapper.toTestingAreaResponseDto(savedTestingArea);
    }

    @Override
    public List<RunResponseDto> getAllRuns() {
        return runMapper.toRunResponseDtos(runRepository.findAll());
    }

    @Override
    @Transactional
    public AreaPlatformAssignmentResponseDto createAssignment(AreaPlatformAssignmentRequestDto dto) {
        TestingArea testingArea = testingAreaRepository.findById(dto.getTestingAreaId()).orElseThrow(
                ()-> new EntityNotFoundException("Testing area not found with given id: " + dto.getTestingAreaId())
        );
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                ()-> new EntityNotFoundException("User not found with given id: " + dto.getUserId())
        );

        AreaPlatformAssignment assignment =  areaPlatformAssignmentMapper.toAreaPlatformAssignment(
                dto, user, testingArea
        );
        AreaPlatformAssignment savedAssignment = areaPlatformAssignmentRepository.save(
                assignment
        );
        return areaPlatformAssignmentMapper.toAreaPlatformAssignmentResponseDto(savedAssignment);
    }

    @Override
    public RunResponseDto getRunById(Long runId) {
        Run run = runRepository.findById(runId).orElseThrow(()-> new EntityNotFoundException(
                "Run not found with given id: " + runId));
        return runMapper.toRunResponseDto(run);
    }
}
