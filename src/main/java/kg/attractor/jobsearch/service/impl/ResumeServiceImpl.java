package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ContactInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
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

    @Override
    public int createResume(ResumeDto resumeDto) {
        try {
            return resumeDao.saveResume(resumeDto);
        } catch (Exception e) {
            log.error("An error occurred while creating resume: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to create resume");
        }
    }

    @Override
    public ResumeDto createFullResume(ResumeCreatingDto request) {
        ResumeDto resumeDto = request.getResumeDto();
        List<EducationInfoDto> educationInfoDtos = request.getEducationInfoDtos();
        List<WorkExperienceInfoDto> workExperienceInfoDtos = request.getWorkExperienceInfoDtos();
        List<ContactInfoDto> contactInfoDtos = request.getContactInfoDtos();

        int resumeId;
        try {
            resumeId = createResume(resumeDto);
        } catch (Exception e) {
            log.error("An error occurred while creating resume: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to create resume");
        }

        resumeDto.setId((long) resumeId);

        if (educationInfoDtos != null) {
            for (EducationInfoDto educationInfoDto : educationInfoDtos) {
                educationInfoDto.setResumeId(resumeId);
                try {
                    educationInfoService.saveEducationInfo(educationInfoDto);
                } catch (Exception e) {
                    log.error("An error occurred while saving education info: {}", e.getMessage());
                    throw new CanNotFindResumeException("Unable to save education info for resume ID " + resumeId);
                }
            }
        }

        if (workExperienceInfoDtos != null) {
            for (WorkExperienceInfoDto workExperienceInfoDto : workExperienceInfoDtos) {
                workExperienceInfoDto.setResumeId(resumeId);
                try {
                    workExperienceInfoService.saveWorkExperienceInfo(workExperienceInfoDto);
                } catch (Exception e) {
                    log.error("An error occurred while saving work experience info: {}", e.getMessage());
                    throw new CanNotFindResumeException("Unable to save work experience info for resume ID " + resumeId);
                }
            }
        }

        if (contactInfoDtos != null) {
            for (ContactInfoDto contactInfoDto : contactInfoDtos) {
                contactInfoDto.setResumeId(resumeId);
                try {
                    contactInfoService.saveContactInfo(contactInfoDto);
                } catch (Exception e) {
                    log.error("An error occurred while saving contact info: {}", e.getMessage());
                    throw new CanNotFindResumeException("Unable to save contact info for resume ID " + resumeId);
                }
            }
        }

        ResumeDto createdResume = resumeDao.findResumeById((long) resumeId);
        return createdResume;
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        try {
            return resumeDao.findAllResumes();
        } catch (Exception e) {
            log.error("An error occurred while fetching all resumes: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch all resumes");
        }
    }

    @Override
    public void updateResume(ResumeDto resumeDto) {
        try {
            resumeDao.updateResume(resumeDto);
        } catch (Exception e) {
            log.error("An error occurred while updating resume: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to update resume");
        }
    }

    @Override
    public void updateFullResume(ResumeCreatingDto request) {
        ResumeDto resumeDto = request.getResumeDto();
        List<EducationInfoDto> educationInfoDtos = request.getEducationInfoDtos();
        List<WorkExperienceInfoDto> workExperienceInfoDtos = request.getWorkExperienceInfoDtos();
        List<ContactInfoDto> contactInfoDtos = request.getContactInfoDtos();

        try {
            updateResume(resumeDto);
        } catch (Exception e) {
            log.error("An error occurred while updating resume: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to update resume");
        }

        Long resumeId = resumeDto.getId();

        if (educationInfoDtos != null) {
            for (EducationInfoDto educationInfoDto : educationInfoDtos) {
                educationInfoDto.setResumeId(resumeId.intValue());
                try {
                    if (educationInfoDto.getId() == null) {
                        educationInfoService.saveEducationInfo(educationInfoDto);
                    } else {
                        educationInfoService.updateEducationInfo(educationInfoDto);
                    }
                } catch (Exception e) {
                    log.error("An error occurred while saving/updating education info: {}", e.getMessage());
                    throw new CanNotFindResumeException("Unable to save/update education info for resume ID " + resumeId);
                }
            }
        }

        if (workExperienceInfoDtos != null) {
            for (WorkExperienceInfoDto workExperienceInfoDto : workExperienceInfoDtos) {
                workExperienceInfoDto.setResumeId(resumeId.intValue());
                try {
                    if (workExperienceInfoDto.getId() == null) {
                        workExperienceInfoService.saveWorkExperienceInfo(workExperienceInfoDto);
                    } else {
                        workExperienceInfoService.updateWorkExperienceInfo(workExperienceInfoDto);
                    }
                } catch (Exception e) {
                    log.error("An error occurred while saving/updating work experience info: {}", e.getMessage());
                    throw new CanNotFindResumeException("Unable to save/update work experience info for resume ID " + resumeId);
                }
            }
        }

        if (contactInfoDtos != null) {
            for (ContactInfoDto contactInfoDto : contactInfoDtos) {
                contactInfoDto.setResumeId(resumeId.intValue());
                try {
                    if (contactInfoDto.getId() == null) {
                        contactInfoService.saveContactInfo(contactInfoDto);
                    } else {
                        contactInfoService.updateContactInfo(contactInfoDto);
                    }
                } catch (Exception e) {
                    log.error("An error occurred while saving/updating contact info: {}", e.getMessage());
                    throw new CanNotFindResumeException("Unable to save/update contact info for resume ID " + resumeId);
                }
            }
        }
    }

    @Override
    public void deleteResume(Long id) {
        try {
            resumeDao.deleteResume(id);
        } catch (Exception e) {
            log.error("An error occurred while deleting resume with ID {}: {}", id, e.getMessage());
            throw new CanNotFindResumeException("Unable to delete resume with ID " + id);
        }
    }

    @Override
    public List<ResumeDto> getResumesByCategory(Long categoryId) {
        try {
            List<ResumeDto> resumes = resumeDao.findResumesByCategory(categoryId);
            if (resumes.isEmpty()) {
                throw new CanNotFindResumeException("No resumes found for category ID " + categoryId);
            }
            return resumes;
        } catch (Exception e) {
            log.error("An error occurred while fetching resumes by category: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resumes by category ID " + categoryId);
        }
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Integer applicantId) {
        try {
            List<ResumeDto> resumes = resumeDao.findResumesByApplicantId(applicantId);
            if (resumes.isEmpty()) {
                throw new CanNotFindResumeException("No resumes found for applicant ID " + applicantId);
            }
            return resumes;
        } catch (Exception e) {
            log.error("An error occurred while fetching resumes by applicant ID: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resumes by applicant ID " + applicantId);
        }
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        try {
            ResumeDto resumeDto = resumeDao.findResumeById(id);
            if (resumeDto == null) {
                throw new CanNotFindResumeException("Resume not found with ID " + id);
            }
            return resumeDto;
        } catch (Exception e) {
            log.error("An error occurred while fetching resume with ID {}: {}", id, e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resume with ID " + id);
        }
    }

    @Override
    public List<ResumeDto> getResumesByUserId(Long userId) {
        try {
            List<ResumeDto> resumes = resumeDao.findResumesByUserId(userId);
            if (resumes.isEmpty()) {
                throw new CanNotFindResumeException("No resumes found for user ID " + userId);
            }
            return resumes;
        } catch (Exception e) {
            log.error("An error occurred while fetching resumes by user ID {}: {}", userId, e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resumes by user ID " + userId);
        }
    }
}
