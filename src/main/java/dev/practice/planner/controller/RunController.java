package dev.practice.planner.controller;

import dev.practice.planner.dtos.*;
import dev.practice.planner.service.RunService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class RunController {

    private final RunService runService;

    @PostMapping("runs")
    public ResponseEntity<RunResponseDto> createRun(@Valid @RequestBody RunRequestDto dto) {
        return new ResponseEntity<>(runService.createRun(dto), HttpStatus.CREATED);
    }

    @PostMapping("runs/{runId}/testing-areas")
    public ResponseEntity<TestingAreaResponseDto> createTestingArea(@Valid @RequestBody TestingAreaRequestDto dto,
                                                                    @PathVariable Long runId) {
        return new ResponseEntity<>(runService.createTestingArea(dto, runId), HttpStatus.CREATED);
    }

    @PostMapping("assignments")
    public ResponseEntity<AreaPlatformAssignmentResponseDto> createAssignment(
            @Valid @RequestBody AreaPlatformAssignmentRequestDto dto) {
        return new ResponseEntity<>(runService.createAssignment(dto), HttpStatus.CREATED);
    }


    @GetMapping("runs")
    public ResponseEntity<List<RunResponseDto>> getAllRuns() {
        return new ResponseEntity<>(runService.getAllRuns(), HttpStatus.OK);
    }

    @GetMapping("/runs/{runId}")
    public ResponseEntity<RunResponseDto> getRunById(@PathVariable Long runId) {
        return new ResponseEntity<>(runService.getRunById(runId), HttpStatus.OK);
    }
}
