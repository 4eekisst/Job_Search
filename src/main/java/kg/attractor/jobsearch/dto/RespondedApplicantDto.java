package kg.attractor.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RespondedApplicantDto {
    private Long id;
    private Integer resumeId;
    private Integer vacancyId;
    private Boolean confirmation;
}
