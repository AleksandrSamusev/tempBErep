package dev.practice.planner.dtos;

import dev.practice.planner.model.Platform;
import dev.practice.planner.model.TestingArea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaPlatformAssignmentResponseDto {
    private Long id;
    private Platform platform;
    private Long testingAreaId;
    private Long testerId;
    private String testerName;
}
