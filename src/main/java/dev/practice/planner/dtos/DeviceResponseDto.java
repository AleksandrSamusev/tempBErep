package dev.practice.planner.dtos;

import dev.practice.planner.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponseDto {
    private Long id;
    private Long brandId;
    private String brandName;
    private String brandWebsite;
    private String brandLogo;
    private String model;
    private DeviceType type;
    private String os;
    private String osVersion;
}
