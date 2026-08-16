package dev.practice.planner.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AvailabilityRequestDto {
    @NotNull(message = "Week start date cannot be Null")
    private LocalDate weekStartDate;
    @Min(value = 0, message = "Hours cannot be negative")
    private Integer monThuHours;
    @Min(value = 0, message = "Hours cannot be negative")
    private Integer friSunHours;
}
