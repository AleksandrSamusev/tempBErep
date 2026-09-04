package dev.practice.planner.dtos;

import dev.practice.planner.model.Platform;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaPlatformAssignmentRequestDto {
    @NotNull(message = "Platform is mandatory")
    private Platform platform;

    @NotNull(message = "Testing area ID is mandatory")
    private Long testingAreaId;

    @NotNull(message = "User ID is mandatory")
    private Long userId;
}
