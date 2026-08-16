package dev.practice.planner.service.impl;

import dev.practice.planner.dtos.*;
import dev.practice.planner.exception.BrandNotFoundException;
import dev.practice.planner.model.Availability;
import dev.practice.planner.model.Brand;
import dev.practice.planner.model.Device;
import dev.practice.planner.model.User;
import dev.practice.planner.repository.BrandRepository;
import dev.practice.planner.repository.UserRepository;
import dev.practice.planner.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;


    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        if(request.getDevices() != null) {
            Set<Long> brandIds = request.getDevices().stream().map(DeviceRequestDto::getBrandId).collect(Collectors.toSet());
            List<Brand> brands = brandRepository.findAllById(brandIds);
            Map<Long, Brand> brandMap = brands.stream().collect(Collectors.toMap(Brand::getId, Function.identity()));
            for (DeviceRequestDto dto : request.getDevices()) {

                Brand brand = Optional.ofNullable(brandMap.get(dto.getBrandId())).orElseThrow(() -> new BrandNotFoundException(dto.getBrandId()));

                Device device = new Device();
                device.setBrand(brand);
                device.setModel(dto.getModel());
                device.setType(dto.getType());
                device.setOs(dto.getOs());
                device.setOsVersion(dto.getOsVersion());
                device.setOwner(user);
                user.getDevices().add(device);
            }
        }
        User savedUser = userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setFirstName(savedUser.getFirstName());
        responseDto.setLastName(savedUser.getLastName());
        responseDto.setEmail(savedUser.getEmail());

        List<DeviceResponseDto> deviceDtos = new ArrayList<>();

        for(Device device: savedUser.getDevices()) {
            DeviceResponseDto resp = new DeviceResponseDto();
            resp.setId(device.getId());
            resp.setBrandId(device.getBrand().getId());
            resp.setBrandName(device.getBrand().getName());
            resp.setBrandWebsite(device.getBrand().getWebsite());
            resp.setBrandLogo(device.getBrand().getLogoUrl());
            resp.setModel(device.getModel());
            resp.setType(device.getType());
            resp.setOs(device.getOs());
            resp.setOsVersion(device.getOsVersion());
            deviceDtos.add(resp);
        }
        responseDto.setDevices(deviceDtos);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAllWithDevices();
        return users.stream().map(this::mapToUserResponseDto).toList();
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public DeviceResponseDto addDevice(Long userId, DeviceRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found with given id: " + userId));
        Brand brand = brandRepository.findById(dto.getBrandId()).orElseThrow(()-> new EntityNotFoundException("Brand not found with given id: " + dto.getBrandId()));
        Device device = new Device();
        device.setBrand(brand);
        device.setModel(dto.getModel());
        device.setOs(dto.getOs());
        device.setOsVersion(dto.getOsVersion());
        device.setType(dto.getType());
        user.getDevices().add(device);
        device.setOwner(user);
        User savedUser = userRepository.save(user);
        Device savedDevice = savedUser.getDevices().getLast();
        return new DeviceResponseDto(
                savedDevice.getId(),
                brand.getId(),
                brand.getName(),
                brand.getWebsite(),
                brand.getLogoUrl(),
                savedDevice.getModel(),
                savedDevice.getType(),
                savedDevice.getOs(),
                savedDevice.getOsVersion()
        );
    }

    @Override
    @Transactional
    public DeviceResponseDto updateDevice(Long userId, Long deviceId, DeviceRequestDto dto) {
       User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found with given id: " + userId));
       Device device = user.getDevices().stream().filter(d -> Objects.equals(d.getId(), deviceId)).findFirst().orElseThrow(
               ()-> new EntityNotFoundException("Device not found with given id: " + deviceId));
       Brand brand = brandRepository.findById(dto.getBrandId()).orElseThrow(
               ()->new EntityNotFoundException("Brand not found with given id: " + dto.getBrandId()));
       device.setBrand(brand);
       device.setModel(dto.getModel());
       device.setType(dto.getType());
       device.setOs(dto.getOs());
       device.setOsVersion(dto.getOsVersion());

       User savedUser = userRepository.save(user);
       Device savedDevice = savedUser.getDevices().stream().filter(d -> Objects.equals(d.getId(), deviceId)).findFirst().orElseThrow();
       return new DeviceResponseDto(
               savedDevice.getId(),
               brand.getId(),
               brand.getName(),
               brand.getWebsite(),
               brand.getLogoUrl(),
               savedDevice.getModel(),
               savedDevice.getType(),
               savedDevice.getOs(),
               savedDevice.getOsVersion()
       );
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with given id: " + userId));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        User savedUser = userRepository.save(user);
        return mapToUserResponseDto(savedUser);
    }

    private UserResponseDto mapToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());

        List<DeviceResponseDto> deviceDtos =
                user.getDevices()
                        .stream()
                        .map(this::mapToDeviceResponseDto)
                        .toList();

        List<AvailabilityResponseDto> availabilityDtos =
                user.getAvailabilities()
                        .stream()
                        .sorted(Comparator.comparing(Availability::getWeekStartDate))
                        .map(this::toAvailabilityResponse)
                        .toList();

        LocalDate currentMonday =
                LocalDate.now()
                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDate nextMonday =
                currentMonday.plusWeeks(1);

        AvailabilityResponseDto currentWeekAvailability =
                user.getAvailabilities()
                        .stream()
                        .filter(a -> a.getWeekStartDate().equals(currentMonday))
                        .findFirst()
                        .map(this::toAvailabilityResponse)
                        .orElse(null);

        AvailabilityResponseDto nextWeekAvailability =
                user.getAvailabilities()
                        .stream()
                        .filter(a -> a.getWeekStartDate().equals(nextMonday))
                        .findFirst()
                        .map(this::toAvailabilityResponse)
                        .orElse(null);

        userResponseDto.setDevices(deviceDtos);
        userResponseDto.setAvailabilities(availabilityDtos);

        userResponseDto.setCurrentWeekAvailability(currentWeekAvailability);
        userResponseDto.setNextWeekAvailability(nextWeekAvailability);

        return userResponseDto;
    }

    private DeviceResponseDto mapToDeviceResponseDto(Device device) {
        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();
        deviceResponseDto.setId(device.getId());
        deviceResponseDto.setBrandId(device.getBrand().getId());
        deviceResponseDto.setBrandName(device.getBrand().getName());
        deviceResponseDto.setBrandWebsite(device.getBrand().getWebsite());
        deviceResponseDto.setBrandLogo(device.getBrand().getLogoUrl());
        deviceResponseDto.setModel(device.getModel());
        deviceResponseDto.setType(device.getType());
        deviceResponseDto.setOs(device.getOs());
        deviceResponseDto.setOsVersion(device.getOsVersion());
        return deviceResponseDto;
    }

    private AvailabilityResponseDto toAvailabilityResponse(Availability availability) {
        return new AvailabilityResponseDto(
                availability.getId(),
                availability.getWeekStartDate(),
                availability.getMonThuHours(),
                availability.getFriSunHours()
        );
    }
}
