package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "runs")
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Run start cannot be null")
    @Column(name = "run_start", nullable = false)
    private LocalDateTime runStart;
    
    @NotNull(message = "Run end cannot be null")
    @Column(name = "run_end", nullable = false)
    private LocalDateTime runEnd;
    
    @Column(name = "title", nullable = false)
    @NotBlank(message = "title cannot be blank")
    private String title;
    
    @Column(name = "description", length = 2000)
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "run_platforms", joinColumns = @JoinColumn(name = "run_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "platform_name")
    @Size(min = 1, message = "At least one platform should be defined")
    private List<Platform> platforms = new ArrayList<>();

    @OneToMany(mappedBy = "run", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestingArea> testingAreas = new ArrayList<>();
}
