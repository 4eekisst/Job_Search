package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be positive")
    private Long categoryId;

    @NotNull(message = "Author ID cannot be null")
    @Positive(message = "Author ID must be positive")
    private Long authorId;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotNull(message = "Experience from cannot be null")
    @Positive(message = "Experience from must be positive")
    private Integer expFrom;

    @NotNull(message = "Experience to cannot be null")
    @Positive(message = "Experience to must be positive")
    private Integer expTo;

    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
