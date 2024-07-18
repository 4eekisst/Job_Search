package kg.attractor.jobsearch.model;

import lombok.Data;

@Data

public class RespondedApplicant {
    private Long id;
    private Integer resumeId;
    private Integer vacancyId;
    private Boolean confirmation;
}

