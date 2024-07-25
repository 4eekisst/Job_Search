package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    ResumeDto createResume(ResumeDto resumeDto);
    List<ResumeDto> getAllResumes();
    List<ResumeDto> getResumesByCategory(Long categoryId);
    ResumeDto editingResume(Long id, ResumeDto resumeDto);
    void deleteResume(Long id);
}
