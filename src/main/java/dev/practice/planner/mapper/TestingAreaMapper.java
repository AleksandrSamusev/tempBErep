package dev.practice.planner.mapper;

import dev.practice.planner.dtos.TestingAreaRequestDto;
import dev.practice.planner.model.Run;
import dev.practice.planner.model.TestingArea;
import org.springframework.stereotype.Component;

@Component
public class TestingAreaMapper {

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

}
