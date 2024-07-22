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

    @PutMapping("/update/{id}")
    public ResponseEntity<ResumeDto> editResume(@PathVariable Long id, @RequestBody ResumeDto resumeDto) {
        ResumeDto updatedResumeDto = resumeService.editingResume(id, resumeDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedResumeDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
