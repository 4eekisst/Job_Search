package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.dto.customDto.ResumeEditDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("resumesRest")
@RequiredArgsConstructor
@RequestMapping("api/resumes")


public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<String> createResume(@RequestBody @Valid ResumeCreatingDto request) {
        resumeService.createFullResume(request);
        return ResponseEntity.ok("Resume created successfully");
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
    public ResponseEntity<String> updateResume(@RequestBody @Valid ResumeEditDto resumeEditDto) {
        resumeService.updateFullResume(resumeEditDto);
        return ResponseEntity.ok("Resume updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable @Positive(message = "Resume ID must be positive") Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicantId(@PathVariable @Positive(message = "Applicant ID must be positive") Integer applicantId) {
        try {
            List<ResumeDto> resumes = resumeService.getResumesByApplicantId(applicantId);
            return ResponseEntity.ok(resumes);
        } catch (CanNotFindResumeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
