package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.service.ContactInfoService;
import kg.attractor.jobsearch.service.EducationInfoService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resumes")


public class ResumeController {
    private final ResumeService resumeService;
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final ContactInfoService contactInfoService;

    @PostMapping("/create")
    public ResponseEntity<String> createResume(
            @RequestBody ResumeCreatingDto request) {

        ResumeDto resumeDto = request.getResumeDto();
        List<EducationInfoDto> educationInfoDtos = request.getEducationInfoDtos();
        List<WorkExperienceInfoDto> workExperienceInfoDtos = request.getWorkExperienceInfoDtos();
        List<ContactInfoDto> contactInfoDtos = request.getContactInfoDtos();

        int resumeId = resumeService.createResume(resumeDto);
        resumeDto.setId((long) resumeId);

        for (EducationInfoDto educationInfoDto : educationInfoDtos) {
            educationInfoDto.setResumeId(resumeId);
            educationInfoService.saveEducationInfo(educationInfoDto);
        }

        for (WorkExperienceInfoDto workExperienceInfoDto : workExperienceInfoDtos) {
            workExperienceInfoDto.setResumeId(resumeId);
            workExperienceInfoService.saveWorkExperienceInfo(workExperienceInfoDto);
        }

        for (ContactInfoDto contactInfoDto : contactInfoDtos) {
            contactInfoDto.setResumeId(resumeId);
            contactInfoService.saveContactInfo(contactInfoDto);
        }

        return ResponseEntity.ok("Resume created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        List<ResumeDto> resumes = resumeService.getAllResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@PathVariable Long categoryId) {
        List<ResumeDto> resumes = resumeService.getResumesByCategory(categoryId);
        return ResponseEntity.ok(resumes);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateResume(@RequestBody ResumeDto resumeDto) {
        resumeService.updateResume(resumeDto);
        return ResponseEntity.ok("Resume updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicantId(@PathVariable Integer applicantId) {
        List<ResumeDto> resumes = resumeService.getResumesByApplicantId(applicantId);
        return ResponseEntity.ok(resumes);
    }
}
