package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
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

    @PostMapping("/create")
    public ResponseEntity<String> createResume(@RequestBody @Valid ResumeCreatingDto request) {
        resumeService.createFullResume(request);
        return ResponseEntity.ok("Resume created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        List<ResumeDto> resumes = resumeService.getAllResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@PathVariable @Positive(message = "Category ID must be positive") Long categoryId) {
        try {
            List<ResumeDto> resumes = resumeService.getResumesByCategory(categoryId);
            return ResponseEntity.ok(resumes);
        } catch (CanNotFindResumeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateResume(@RequestBody @Valid ResumeCreatingDto request) {
        resumeService.updateFullResume(request);
        return ResponseEntity.ok("Resume updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable @Positive(message = "Resume ID must be positive") Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicantId(@PathVariable @Positive(message = "Applicant ID must be positive") Integer applicantId) {
        List<ResumeDto> resumes = resumeService.getResumesByApplicantId(applicantId);
        return ResponseEntity.ok(resumes);
    }
}
