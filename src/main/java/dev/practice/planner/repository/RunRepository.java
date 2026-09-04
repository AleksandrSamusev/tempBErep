package dev.practice.planner.repository;

import dev.practice.planner.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Run, Long> {

}
