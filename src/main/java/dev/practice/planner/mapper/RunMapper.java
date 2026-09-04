package dev.practice.planner.mapper;

import dev.practice.planner.dtos.RunRequestDto;
import dev.practice.planner.dtos.RunResponseDto;
import dev.practice.planner.model.Run;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RunMapper {

    private final TestingAreaMapper testingAreaMapper;

    public Run toRun(RunRequestDto dto) {
        Run run = new Run();
        run.setRunStart(dto.getRunStart());
        run.setRunEnd(dto.getRunEnd());
        run.setTitle(dto.getTitle());
        run.setDescription(dto.getDescription());

        if (dto.getPlatforms() != null) {
            run.getPlatforms().clear();
            run.getPlatforms().addAll(dto.getPlatforms());
        }
        return run;
    }

    public RunResponseDto toRunResponseDto(Run run) {
        if(run == null) {
            return null;
        }
        RunResponseDto dto = new RunResponseDto();
        dto.setId(run.getId());
        dto.setRunStart(run.getRunStart());
        dto.setRunEnd(run.getRunEnd());
        dto.setTitle(run.getTitle());
        dto.setDescription(run.getDescription());
        if (run.getPlatforms() != null) {
            dto.getPlatforms().clear();
            dto.getPlatforms().addAll(run.getPlatforms());
        }
        dto.setTestingAreas(testingAreaMapper.toTestingAreaResponseDtos(run.getTestingAreas()));
        return dto;
    }

    public List<RunResponseDto> toRunResponseDtos(List<Run> runs) {
        if(runs == null || runs.isEmpty()) {
            return new ArrayList<>();
        }
        return runs.stream().map(this::toRunResponseDto).toList();
    }
}
