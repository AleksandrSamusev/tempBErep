package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
        name = "user_availabilities",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "availability_date"}
                )
        }
)
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "availability_date", nullable = false)
    @NotNull(message = "Date cannot be Null")
    private LocalDate date;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status cannot be blank")
    private String status;
}
