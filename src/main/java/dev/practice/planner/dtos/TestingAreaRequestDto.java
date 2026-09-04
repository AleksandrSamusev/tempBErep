package dev.practice.planner.dtos;

import dev.practice.planner.model.Complexity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TestingAreaRequestDto {
    @NotBlank(message = "tag cannot be blank")
    @Size(max = 200, message = "Tag size should not exceed 200 characters")
    private String tag;
    @NotBlank(message = "location cannot be blank")
    @Size(max = 200, message = "Location size should not exceed 200 characters")
    private String location;
    @NotBlank(message = "URL cannot be blank")
    @URL(message = "Invalid URL format")
    private String url;
    @NotNull(message = "Complexity cannot be null")
    private Complexity complexity;
    @Size(max = 2000, message = "Notes size should not exceed 2000 characters")
    private String notes;
}
