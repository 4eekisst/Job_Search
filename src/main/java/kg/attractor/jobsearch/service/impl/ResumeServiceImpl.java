package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ContactInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.service.ContactInfoService;
import kg.attractor.jobsearch.service.EducationInfoService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final ContactInfoService contactInfoService;

    @Override public int createResume(ResumeDto resumeDto) {return resumeDao.saveResume(resumeDto);}

    @Override
    public void createFullResume(ResumeCreatingDto request) {
        ResumeDto resumeDto = request.getResumeDto();
        List<EducationInfoDto> educationInfoDtos = request.getEducationInfoDtos();
        List<WorkExperienceInfoDto> workExperienceInfoDtos = request.getWorkExperienceInfoDtos();
        List<ContactInfoDto> contactInfoDtos = request.getContactInfoDtos();

        int resumeId = createResume(resumeDto);
        resumeDto.setId((long) resumeId);

        if (educationInfoDtos != null) {
            for (EducationInfoDto educationInfoDto : educationInfoDtos) {
                educationInfoDto.setResumeId(resumeId);
                educationInfoService.saveEducationInfo(educationInfoDto);
            }
        }

        if (workExperienceInfoDtos != null) {
            for (WorkExperienceInfoDto workExperienceInfoDto : workExperienceInfoDtos) {
                workExperienceInfoDto.setResumeId(resumeId);
                workExperienceInfoService.saveWorkExperienceInfo(workExperienceInfoDto);
            }
        }

        if (contactInfoDtos != null) {
            for (ContactInfoDto contactInfoDto : contactInfoDtos) {
                contactInfoDto.setResumeId(resumeId);
                contactInfoService.saveContactInfo(contactInfoDto);
            }
        }
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        return resumeDao.findAllResumes();
    }

    @Override
    public void updateResume(ResumeDto resumeDto) {
        resumeDao.updateResume(resumeDto);
    }

    @Override
    public void deleteResume(Long id) {
        resumeDao.deleteResume(id);
    }

    @Override
    public List<ResumeDto> getResumesByCategory(Long categoryId) {return resumeDao.findResumesByCategory(categoryId);}

    @Override
    public List<ResumeDto> getResumesByApplicantId(Integer applicantId) {return resumeDao.findResumesByApplicantId(applicantId);}
}
