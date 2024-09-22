package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ContactInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repository.*;
import kg.attractor.jobsearch.service.ContactInfoService;
import kg.attractor.jobsearch.service.EducationInfoService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final ContactInfoService contactInfoService;
    private final ResumeRepository resumeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Resume createResume(ResumeDto resumeDto) {
        User applicant = userRepository.findById(resumeDto.getApplicantId().longValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resume resume = Resume.builder()
                .name(resumeDto.getName())
                .category(categoryRepository.findById(resumeDto.getCategoryId().longValue())
                        .orElseThrow(() -> new RuntimeException("Category not found")))
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .createdDate(LocalDate.now())
                .updateTime(LocalDate.now())
                .applicant(applicant)
                .build();

        Resume savedResume = resumeRepository.save(resume);
        savedResume.setEmail(applicant.getEmail());

        return resumeRepository.save(savedResume);
    }

    @Override
    public ResumeDto createFullResume(ResumeCreatingDto request) {
        ResumeDto resumeDto = request.getResumeDto();
        List<EducationInfoDto> educationInfoDtos = request.getEducationInfoDtos();
        List<WorkExperienceInfoDto> workExperienceInfoDtos = request.getWorkExperienceInfoDtos();
        List<ContactInfoDto> contactInfoDtos = request.getContactInfoDtos();

        Resume savedResume;
        try {
            savedResume = createResume(resumeDto);
        } catch (Exception e) {
            log.error("An error occurred while creating resume: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to create resume");
        }

        resumeDto.setId(savedResume.getId());

        if (educationInfoDtos != null) {
            for (EducationInfoDto educationInfoDto : educationInfoDtos) {
                if (educationInfoDto.getDegree() != null && !educationInfoDto.getDegree().isEmpty() &&
                        educationInfoDto.getInstitution() != null && !educationInfoDto.getInstitution().isEmpty()) {
                    educationInfoDto.setResumeId(savedResume.getId());
                    try {
                        educationInfoService.saveEducationInfo(educationInfoDto);
                    } catch (Exception e) {
                        log.error("An error occurred while saving education info: {}", e.getMessage());
                        throw new CanNotFindResumeException("Unable to save education info for resume ID " + savedResume.getId());
                    }
                }
            }
        }

        if (workExperienceInfoDtos != null) {
            for (WorkExperienceInfoDto workExperienceInfoDto : workExperienceInfoDtos) {
                if (workExperienceInfoDto.getYears() != null && workExperienceInfoDto.getCompanyName() != null && !workExperienceInfoDto.getCompanyName().isEmpty()) {
                    workExperienceInfoDto.setResumeId(savedResume.getId());
                    try {
                        workExperienceInfoService.saveWorkExperienceInfo(workExperienceInfoDto);
                    } catch (Exception e) {
                        log.error("An error occurred while saving work experience info: {}", e.getMessage());
                        throw new CanNotFindResumeException("Unable to save work experience info for resume ID " + savedResume.getId());
                    }
                }
            }
        }

        if (contactInfoDtos != null) {
            for (ContactInfoDto contactInfoDto : contactInfoDtos) {
                if (contactInfoDto.getValue() != null && !contactInfoDto.getValue().isEmpty()) {
                    contactInfoDto.setResumeId(savedResume.getId());
                    try {
                        contactInfoService.saveContactInfo(contactInfoDto);
                    } catch (Exception e) {
                        log.error("An error occurred while saving contact info: {}", e.getMessage());
                        throw new CanNotFindResumeException("Unable to save contact info for resume ID " + savedResume.getId());
                    }
                }
            }
        }

        return resumeDto;
    }

    @Override
    public Page<ResumeDto> getAllResumes(Pageable pageable) {
        try {
            Page<Resume> resumesPage = resumeRepository.findAll(pageable);

            return resumesPage.map(this::getResumeDto);
        } catch (Exception e) {
            log.error("An error occurred while fetching all resumes: {}", e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch all resumes");
        }
    }

    @Override
    public void updateResume(ResumeDto resumeDto) {
        try {
            Resume existingResume = resumeRepository.findById(resumeDto.getId())
                    .orElseThrow(() -> new CanNotFindResumeException("Resume not found with ID " + resumeDto.getId()));

            existingResume.setName(resumeDto.getName());
            existingResume.setSalary(resumeDto.getSalary());
            existingResume.setActive(resumeDto.getIsActive());
            existingResume.setUpdateTime(resumeDto.getUpdateTime().toLocalDate());

            Category category = categoryRepository.findById(resumeDto.getCategoryId().longValue())
                    .orElseThrow(() -> new CanNotFindResumeException("Category not found with ID " + resumeDto.getCategoryId()));
            existingResume.setCategory(category);

            resumeRepository.save(existingResume);
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
                educationInfoDto.setResumeId(resumeId);
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
                workExperienceInfoDto.setResumeId(resumeId);
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
                contactInfoDto.setResumeId(resumeId);
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
            if (resumeRepository.existsById(id)) {
                resumeRepository.deleteById(id);
            } else {
                throw new CanNotFindResumeException("Resume with ID " + id + " does not exist");
            }
        } catch (Exception e) {
            log.error("An error occurred while deleting resume with ID {}: {}", id, e.getMessage());
            throw new CanNotFindResumeException("Unable to delete resume with ID " + id);
        }
    }

    @Override
    public List<ResumeDto> getResumesByCategory(Long categoryId) {
        try {
            List<Resume> resumes = resumeRepository.findByCategoryId(categoryId);
            if (resumes.isEmpty()) {
                throw new CanNotFindResumeException("No resumes found for category ID " + categoryId);
            }
            return resumes.stream()
                    .map(this::getResumeDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching resumes by category ID {}: {}", categoryId, e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resumes by category ID " + categoryId);
        }
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Integer applicantId) {
        try {
            Long applicantIdLong = applicantId.longValue();

            List<Resume> resumes = resumeRepository.findByApplicantId(applicantIdLong);
            if (resumes.isEmpty()) {
                throw new CanNotFindResumeException("No resumes found for applicant ID " + applicantId);
            }

            return resumes.stream()
                    .map(this::getResumeDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching resumes by applicant ID {}: {}", applicantId, e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resumes by applicant ID " + applicantId);
        }
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        try {
            Resume resume = resumeRepository.findById(id)
                    .orElseThrow(() -> new CanNotFindResumeException("Resume not found with ID " + id));

            return getResumeDto(resume);
        } catch (Exception e) {
            log.error("An error occurred while fetching resume with ID {}: {}", id, e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resume with ID " + id);
        }
    }

    @Override
    public Page<ResumeDto> getResumesByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<Resume> resumesPage = resumeRepository.findByApplicantId(userId, pageable);
            return resumesPage.map(this::getResumeDto);
        } catch (Exception e) {
            log.error("An error occurred while fetching resumes by user ID {}: {}", userId, e.getMessage());
            throw new CanNotFindResumeException("Unable to fetch resumes by user ID " + userId);
        }
    }

    private ResumeDto getResumeDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicant().getId().intValue())
                .email(resume.getApplicant().getEmail())
                .name(resume.getName())
                .categoryId(resume.getCategory().getId().intValue())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdDate(resume.getCreatedDate().atStartOfDay())
                .updateTime(resume.getUpdateTime().atStartOfDay())
                .build();
    }
}
