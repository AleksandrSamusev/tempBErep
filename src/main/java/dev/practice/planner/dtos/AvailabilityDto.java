package dev.practice.planner.dtos;
import jakarta.validation.constraints.NotBlank;
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
public class AvailabilityDto {
    @NotNull(message = "Week start date cannot be Null")
    private LocalDate date;
    @NotBlank(message = "Status cannot be blank!")
    private String status;
}
