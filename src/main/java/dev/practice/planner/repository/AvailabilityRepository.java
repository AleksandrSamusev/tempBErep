package dev.practice.planner.repository;

import dev.practice.planner.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    boolean existsByUserIdAndWeekStartDate(Long userId, LocalDate weekStartDate);
}
