package dev.practice.planner.dtos;

import dev.practice.planner.model.Complexity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestingAreaResponseDto {
    private Long id;
    private String tag;
    private String location;
    private String url;
    private Complexity complexity;
    private String notes;
    private Long runId;
    private List<AreaPlatformAssignmentResponseDto> assignments;
}
