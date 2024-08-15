package kg.attractor.jobsearch.dto.customDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserProfileDto {
    private Long id;

//    @NotBlank(message = "Name cannot be blank")
//    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

//    @NotBlank(message = "Surname cannot be blank")
//    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

//    @NotNull(message = "Age cannot be null")
//    @Min(value = 0, message = "Age must be positive")
//    @Max(value = 150, message = "Age must be less than or equal to 150")
    private Integer age;

//    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    private String phoneNumber;
}
