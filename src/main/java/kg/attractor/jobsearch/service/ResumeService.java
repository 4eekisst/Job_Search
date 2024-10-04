package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.dto.customDto.ResumeEditDto;
import kg.attractor.jobsearch.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService {
    Resume createResume(ResumeDto resumeDto);
    ResumeDto createFullResume(ResumeCreatingDto request);
    Page<ResumeDto> getAllResumes(Pageable pageable);
    void updateResume(ResumeDto resumeDto);
    void updateFullResume(ResumeEditDto request);
    void deleteResume(Long id);
    List<ResumeDto> getResumesByCategory(Long categoryId);
    List<ResumeDto> getResumesByApplicantId(Integer applicantId);
    ResumeDto getResumeById(Long id);
    Page<ResumeDto> getResumesByUserId(Long userId, int page, int size);
}
