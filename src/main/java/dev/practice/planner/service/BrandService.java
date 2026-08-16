package dev.practice.planner.service;

import dev.practice.planner.dtos.BrandRequestDto;
import dev.practice.planner.dtos.BrandResponseDto;

import java.util.List;

public interface BrandService {
    BrandResponseDto createBrand(BrandRequestDto dto);

    List<BrandResponseDto> getAllBrands();

    BrandResponseDto getById(Long id);

    void deleteById(Long id);

    BrandResponseDto updateBrand(long id, BrandRequestDto dto);
}
