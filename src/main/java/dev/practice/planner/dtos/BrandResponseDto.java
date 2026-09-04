package dev.practice.planner.dtos;

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
public class BrandResponseDto {
    private Long id;

    @NotBlank(message = "Name is mandatory!")
    @Size(max = 100)
    private String name;

    @URL(message = "Invalid website URL")
    private String website;

    @URL(message = "Invalid logo URL")
    private String logoUrl;

    @NotBlank(message = "Country Code is mandatory!")
    @Size(min=2, max = 2)
    private String countryCode;
}
