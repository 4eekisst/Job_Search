package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {
    @Override
    public ResumeDto createResume(ResumeDto resumeDto) {
        return ResumeDto.builder()
                .id(1L)
                .name(resumeDto.getName())
                .userId(resumeDto.getUserId())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        return Arrays.asList(
                ResumeDto.builder()
                        .id(1L)
                        .name("Adil Rysbaev")
                        .userId(1L)
                        .categoryId(1L)
                        .salary(50000.0)
                        .isActive(true)
                        .createdDate(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build(),
                ResumeDto.builder()
                        .id(2L)
                        .name("Vasya Pupkin")
                        .userId(2L)
                        .categoryId(2L)
                        .salary(60000.0)
                        .isActive(false)
                        .createdDate(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public List<ResumeDto> getResumesByCategory(Long categoryId) {
        List<ResumeDto> allResumes = getAllResumes();
        return allResumes.stream()
                .filter(resume -> resume.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public ResumeDto editingResume(Long id, ResumeDto resumeDto) {
        return ResumeDto.builder()
                .id(id)
                .name(resumeDto.getName())
                .userId(resumeDto.getUserId())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .createdDate(resumeDto.getCreatedDate())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public void deleteResume(Long id) {
        log.info("Resume with id {} has been deleted.", id);
    }
}
