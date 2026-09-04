package dev.practice.planner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory!")
    @Size(max = 100)
    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "website", length = 250)
    @URL(message = "Invalid website URL")
    private String website;

    @Column(name = "logo_url", length = 250)
    private String logoUrl;

    @NotBlank
    @Size(min=2, max = 2)
    @Column(name = "country_code", length = 2)
    private String countryCode;
}
