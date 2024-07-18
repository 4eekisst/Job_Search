package kg.attractor.jobsearch.model;

import lombok.Data;

@Data

public class WorkExperienceInfo {
    private Long id;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}

