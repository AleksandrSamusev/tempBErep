package dev.practice.planner.service.impl;

import dev.practice.planner.dtos.BrandRequestDto;
import dev.practice.planner.dtos.BrandResponseDto;
import dev.practice.planner.model.Brand;
import dev.practice.planner.repository.BrandRepository;
import dev.practice.planner.service.BrandService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;


    @Override
    @Transactional
    public BrandResponseDto createBrand(BrandRequestDto dto) {
        if (brandRepository.existsByNameIgnoreCase(dto.getName().trim())) {
            throw new InvalidParameterException("Brand with name " + dto.getName() + " already exists!");
        }
        Brand brand = new Brand();
        brand.setCountryCode(dto.getCountryCode());
        brand.setName(dto.getName());
        brand.setWebsite(dto.getWebsite());
        brand.setLogoUrl("/logos/" + dto.getName().trim().toLowerCase() + ".svg");
        Brand savedBrand = brandRepository.save(brand);

        return new BrandResponseDto(
                savedBrand.getId(),
                savedBrand.getName(),
                savedBrand.getWebsite(),
                savedBrand.getLogoUrl(),
                savedBrand.getCountryCode()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponseDto> getAllBrands() {
        List<Brand> brands = brandRepository.findAllByOrderByNameAsc();
        return brands.stream().map(brand -> new BrandResponseDto(
                brand.getId(),
                brand.getName(),
                brand.getWebsite(),
                brand.getLogoUrl(),
                brand.getCountryCode()
        )).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponseDto getById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Brand not found with given id: " + id));
        return new BrandResponseDto(
                brand.getId(),
                brand.getName(),
                brand.getWebsite(),
                brand.getLogoUrl(),
                brand.getCountryCode()
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BrandResponseDto updateBrand(long id, BrandRequestDto dto) {
        Brand existingBrand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found with given id: " + id));
        if(dto.getName() != null && !dto.getName().isBlank()) {
            if(brandRepository.existsByNameIgnoreCaseAndIdNot(dto.getName().trim(), id)) {
                throw new InvalidParameterException("Brand with given name: " + dto.getName().trim() + " already exists!");
            }
            existingBrand.setName(dto.getName().trim());
        }
        if(dto.getWebsite() != null && !dto.getWebsite().isBlank()) {
            existingBrand.setWebsite(dto.getWebsite().trim());
        }
        if(dto.getCountryCode() != null && !dto.getCountryCode().isBlank()) {
            existingBrand.setCountryCode(dto.getCountryCode().trim());
        }
        Brand savedBrand = brandRepository.save(existingBrand);
        return new BrandResponseDto(
                savedBrand.getId(),
                savedBrand.getName(),
                savedBrand.getWebsite(),
                savedBrand.getLogoUrl(),
                savedBrand.getCountryCode()
        );
    }
}
