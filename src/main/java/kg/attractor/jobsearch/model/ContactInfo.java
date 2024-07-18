package kg.attractor.jobsearch.model;

import lombok.Data;

@Data

public class ContactInfo {
    private Long id;
    private Integer typeId;
    private Integer resumeId;
    private String value;
}
