package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.repository.EducationInfoRepository;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoRepository educationInfoRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void saveEducationInfo(EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = mapToEducationInfo(educationInfoDto);
        educationInfoRepository.save(educationInfo);
    }

    @Override
    public void updateEducationInfo(EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = mapToEducationInfo(educationInfoDto);
        educationInfoRepository.save(educationInfo);
    }

    private EducationInfo mapToEducationInfo(EducationInfoDto dto) {
        return EducationInfo.builder()
                .id(dto.getId())
                .resume(resumeRepository.findById(Long.valueOf(dto.getResumeId()))
                        .orElseThrow(() -> new CanNotFindResumeException("Resume not found")))
                .institution(dto.getInstitution())
                .program(dto.getProgram())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .degree(dto.getDegree())
                .build();
    }
}