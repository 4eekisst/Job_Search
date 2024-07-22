package kg.attractor.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {
    private Long id;
    private Long vacancyId;
    private Long applicantId;
    private String message;
    private LocalDateTime appliedDate;
    private Boolean isAccepted;
}
