package dev.practice.planner.mapper;

import dev.practice.planner.dtos.DeviceRequestDto;
import dev.practice.planner.dtos.DeviceResponseDto;
import dev.practice.planner.model.Brand;
import dev.practice.planner.model.Device;
import dev.practice.planner.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceMapper {

    public Device toDevice(DeviceRequestDto dto, User user, Brand brand) {
        if (dto == null) {
            return null;
        }
        Device device = new Device();
        device.setOs(dto.getOs());
        device.setType(dto.getType());
        device.setOsVersion(dto.getOsVersion());
        device.setModel(dto.getModel());
        device.setBrand(brand);
        device.setOwner(user);
        return device;
    }

    public DeviceResponseDto toDeviceResponseDto(Device device) {
        if (device == null) {
            return null;
        }
        DeviceResponseDto dto = new DeviceResponseDto();
        dto.setId(device.getId());
        dto.setModel(device.getModel());
        dto.setType(device.getType());
        dto.setOs(device.getOs());
        dto.setOsVersion(device.getOsVersion());
        dto.setBrandId(device.getBrand().getId());
        dto.setBrandLogo(device.getBrand().getLogoUrl());
        dto.setBrandWebsite(device.getBrand().getWebsite());
        dto.setBrandName(device.getBrand().getName());
        return dto;
    }

    public List<DeviceResponseDto> toDeviceResponseDtos(List<Device> devices) {
        if (devices == null || devices.isEmpty()) {
            return new ArrayList<>();
        }
        return devices.stream().map(this::toDeviceResponseDto).toList();
    }
}
