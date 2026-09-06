package dev.practice.planner.dtos;

import dev.practice.planner.model.Platform;
import dev.practice.planner.model.RunStatus;
import dev.practice.planner.model.TestingArea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RunResponseDto {
    private Long id;
    private LocalDateTime runStart;
    private LocalDateTime runEnd;
    private String title;
    private String description;
    private RunStatus runStatus;
    private List<Platform> platforms = new ArrayList<>();
    private List<TestingAreaResponseDto> testingAreas = new ArrayList<>();
}
