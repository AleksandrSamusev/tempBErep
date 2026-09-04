package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "complexities")
@Entity
public class TestingArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "tag cannot be blank")
    @Column(name = "tag")
    private String tag;

    @NotBlank(message = "location cannot be blank")
    @Column(name = "location")
    private String location;

    @URL
    @NotBlank(message = "URL cannot be blank")
    @Column(name = "url")
    private String url;

    @NotNull(message = "complexity is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "complexity", length = 20)
    private Complexity complexity;

    @Size(max = 1000)
    @Column(name = "notes", length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "run_id")
    private Run run;

    @OneToMany(mappedBy = "testingArea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaPlatformAssignment> assignments = new ArrayList<>();
}
