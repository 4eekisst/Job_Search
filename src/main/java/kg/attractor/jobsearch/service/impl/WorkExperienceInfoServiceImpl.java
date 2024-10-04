package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.repository.WorkExperienceInfoRepository;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void saveWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto) {
        WorkExperienceInfo workExperienceInfo = mapToWorkExperienceInfo(workExperienceInfoDto);
        workExperienceInfoRepository.save(workExperienceInfo);
    }

    @Override
    public void updateWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto) {
        WorkExperienceInfo workExperienceInfo = mapToWorkExperienceInfo(workExperienceInfoDto);
        workExperienceInfoRepository.save(workExperienceInfo);
    }

    @Override
    public List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Long resumeId) {
        List<WorkExperienceInfo> experiences = workExperienceInfoRepository.findByResumeId(resumeId);
        return experiences.stream()
                .map(this::mapToWorkExperienceInfoDto)
                .collect(Collectors.toList());
    }

    private WorkExperienceInfo mapToWorkExperienceInfo(WorkExperienceInfoDto dto) {
        return WorkExperienceInfo.builder()
                .id(dto.getId())
                .resume(resumeRepository.findById(Long.valueOf(dto.getResumeId()))
                        .orElseThrow(() -> new CanNotFindResumeException("Resume not found")))
                .years(dto.getYears())
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .responsibilities(dto.getResponsibilities())
                .build();
    }

    private WorkExperienceInfoDto mapToWorkExperienceInfoDto(WorkExperienceInfo info) {
        return WorkExperienceInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResume().getId())
                .years(info.getYears())
                .companyName(info.getCompanyName())
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .build();
    }
}

