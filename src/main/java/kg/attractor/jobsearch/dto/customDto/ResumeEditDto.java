package kg.attractor.jobsearch.dto.customDto;

import kg.attractor.jobsearch.dto.ContactInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ResumeEditDto {
    private ResumeDto resumeDto;
    private List<EducationInfoDto> educationInfoDtos;
    private List<WorkExperienceInfoDto> workExperienceInfoDtos;
    private List<ContactInfoDto> contactInfoDtos;
}
