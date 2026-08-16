package dev.practice.planner.controller;

import dev.practice.planner.dtos.BrandRequestDto;
import dev.practice.planner.dtos.BrandResponseDto;
import dev.practice.planner.service.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    ResponseEntity<BrandResponseDto> createBrand(@Valid @RequestBody BrandRequestDto dto) {
        return new ResponseEntity<>(brandService.createBrand(dto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<BrandResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<BrandResponseDto> updateBrand(@PathVariable long id, @RequestBody BrandRequestDto dto) {
        return new ResponseEntity<>(brandService.updateBrand(id, dto), HttpStatus.OK);
    }
}
