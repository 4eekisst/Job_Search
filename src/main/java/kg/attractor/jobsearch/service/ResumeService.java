package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;

import java.util.List;

public interface ResumeService {
    int createResume(ResumeDto resumeDto);
    void createFullResume(ResumeCreatingDto request);
    List<ResumeDto> getAllResumes();

    void updateResume(ResumeDto resumeDto);

    void updateFullResume(ResumeCreatingDto request);
    void deleteResume(Long id);
    List<ResumeDto> getResumesByCategory(Long categoryId);
    List<ResumeDto> getResumesByApplicantId(Integer applicantId);
}
