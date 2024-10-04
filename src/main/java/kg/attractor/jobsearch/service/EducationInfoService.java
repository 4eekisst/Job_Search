package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.EducationInfoDto;

import java.util.List;

public interface EducationInfoService {
    void saveEducationInfo(EducationInfoDto educationInfoDto);

    void updateEducationInfo(EducationInfoDto educationInfoDto);

    List<EducationInfoDto> getEducationByResumeId(Long resumeId);
}
