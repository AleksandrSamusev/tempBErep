package dev.practice.planner.repository;

import dev.practice.planner.model.TestingArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingAreaRepository extends JpaRepository<TestingArea, Long> {
}
