package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RespondedApplicantDto {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Resume ID cannot be null")
    @Positive(message = "Resume ID must be positive")
    private Integer resumeId;

    @NotNull(message = "Vacancy ID cannot be null")
    @Positive(message = "Vacancy ID must be positive")
    private Integer vacancyId;

    private Boolean confirmation;
    private String message;
    private String resumeName;
    private String vacancyName;
}
