package dev.practice.planner.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "First name is mandatory!")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email address!")
    @Size(max = 255)
    private String email;

    private List<DeviceRequestDto> devices = new ArrayList<>();
}
