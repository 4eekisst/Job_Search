package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/resumes")
    public String listResumes(Model model) {
        List<ResumeDto> resumes = resumeService.getAllResumes();
        model.addAttribute("resumes", resumes);
        return "resume/resumes";
    }

    @GetMapping("/resume/info/{id}")
    public String resumeInfo(@PathVariable Long id, Model model) {
        ResumeDto resume = resumeService.getResumeById(id);
        model.addAttribute("resume", resume);
        return "resume/resumeInfo";
    }
}
