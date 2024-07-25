package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    void createResume(ResumeDto resumeDto);
    List<ResumeDto> getAllResumes();
    List<ResumeDto> getResumesByCategory(int categoryId);
    void updateResume(ResumeDto resumeDto);
    void deleteResume(Long id);
}
