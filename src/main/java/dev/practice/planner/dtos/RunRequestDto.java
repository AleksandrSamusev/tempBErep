package dev.practice.planner.dtos;

import dev.practice.planner.model.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class RunRequestDto {
    @NotNull(message = "Run start cannot be null")
    private LocalDateTime runStart;
    @NotNull(message = "Run end cannot be null")
    private LocalDateTime runEnd;
    @NotBlank(message = "title cannot be blank")
    private String title;
    private String description;
    @Size(min = 1, message = "At least one platform should be defined")
    private List<Platform> platforms = new ArrayList<>();
}
