package dev.practice.planner.repository;

import dev.practice.planner.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Brand> findAllByOrderByNameAsc();
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
}
