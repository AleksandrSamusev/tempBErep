package dev.practice.planner.repository;

import dev.practice.planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT distinct u from User u left join fetch u.devices d left join fetch d.brand")
    List<User> findAllWithDevices();
}
