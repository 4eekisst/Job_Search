package kg.attractor.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResumeDto {
    private Long id;
    private Integer applicantId;
    private String name;
    private Integer categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
}
