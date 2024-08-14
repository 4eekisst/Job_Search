package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be positive")
    @Max(value = 150, message = "Age must be less than or equal to 150")
    private Integer age;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 20, message = "Password length must be between 4 and 20 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$", message = "Password should contain at least one uppercase letter, one number, and one lowercase letter")
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    private String phoneNumber;

    private String avatar;

    private Boolean enabled;

    @NotNull(message = "Authority ID cannot be null")
    private Long authority_id;
}
