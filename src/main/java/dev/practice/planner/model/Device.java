package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Brand is mandatory!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Size(max = 100)
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotNull(message = "Device type is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private DeviceType type;

    @Size(max = 50)
    @Column(length = 50)
    private String os;

    @Size(max = 50)
    @Column(name = "os_version", length = 50)
    private String osVersion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
}