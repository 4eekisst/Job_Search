package kg.attractor.jobsearch.model;

import lombok.Data;

import java.time.LocalDate;
@Data

public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer getExpTo;
    private Boolean isActive;
    private Integer authorId;
    private LocalDate createdDate;
    private LocalDate updateTime;
}