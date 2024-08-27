package kg.attractor.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 4, max = 20, message = "Password length must be between 4 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z]).*$", message = "Password should contain at least one letter")
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    private String phoneNumber;

    private String avatar;

    private Boolean enabled;

    @JsonProperty("authority_id")
    private Long authorityId;
}
