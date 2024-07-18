package kg.attractor.jobsearch.model;

import lombok.Data;

import java.time.LocalDate;
@Data

public class Message {
    private Long id;
    private Integer respondedApplicant;
    private String text;
    private LocalDate timeStamp;
}
