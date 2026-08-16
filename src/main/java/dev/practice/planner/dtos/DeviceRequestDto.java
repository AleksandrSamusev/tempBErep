package dev.practice.planner.dtos;

import dev.practice.planner.model.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequestDto {
    @NotNull(message = "Brand is mandatory!")
    private Long brandId;
    @NotBlank(message = "Model is mandatory")
    @Size(max = 100)
    private String model;
    @NotNull(message = "Device type is mandatory")
    private DeviceType type;
    @Size(max = 50)
    private String os;
    @Size(max = 50)
    private String osVersion;
}
