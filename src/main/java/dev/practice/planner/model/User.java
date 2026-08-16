package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name is mandatory!")
    @Column(name = "first_name", nullable = false, length = 100)
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last Name is mandatory!")
    @Column(name = "last_name", nullable = false, length = 100)
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid email address!")
    @Column(nullable = false, unique = true, length = 255)
    @Size(max = 255)
    private String email;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Device> devices = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Availability> availabilities;
}
