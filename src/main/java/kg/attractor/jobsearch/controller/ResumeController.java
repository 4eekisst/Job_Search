package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
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
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto) {
        ResumeDto createdResume = resumeService.createResume(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResume);
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
}
