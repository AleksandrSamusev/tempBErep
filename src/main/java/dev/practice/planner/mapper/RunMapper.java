package dev.practice.planner.mapper;

import dev.practice.planner.dtos.RunRequestDto;
import dev.practice.planner.model.Run;
import org.springframework.stereotype.Component;

@Component
public class RunMapper {

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
}
