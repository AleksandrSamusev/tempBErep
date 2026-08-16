package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "availabilities",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "week_start_date"}
                )
        }
)
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "week_start_date", nullable = false)
    @NotNull(message = "Week start date cannot be Null")
    private LocalDate weekStartDate;

    @Column(name = "mon_thu_hours")
    @Min(value = 0, message = "Hours cannot be negative")
    private Integer monThuHours;

    @Column(name = "fri_sun_hours")
    @Min(value = 0, message = "Hours cannot be negative")
    private Integer friSunHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
