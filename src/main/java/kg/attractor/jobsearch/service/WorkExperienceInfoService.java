package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;

import java.util.List;

public interface WorkExperienceInfoService {
    void saveWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto);

    void updateWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto);

    List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Long resumeId);
}
