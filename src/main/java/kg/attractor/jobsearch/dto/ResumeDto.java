package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResumeDto {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Applicant ID cannot be null")
    @Positive(message = "Applicant ID must be positive")
    private Integer applicantId;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be positive")
    private Integer categoryId;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    private Double salary;
    private Boolean isActive;
    private String createdDate;
    private String updateTime;
}
