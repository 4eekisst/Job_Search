package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ContactTypeService contactTypeService;

    @GetMapping("/resumes")
    public String listResumes(Model model) {
        List<ResumeDto> resumes = resumeService.getAllResumes();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (ResumeDto resume : resumes) {
            resume.setCreatedDateStr(resume.getCreatedDate().format(formatter));
        }
        model.addAttribute("resumes", resumes);
        return "resume/resumes";
    }

    @GetMapping("/resume/info/{id}")
    public String resumeInfo(@PathVariable Long id, Model model, Authentication authentication) {
        ResumeDto resume = resumeService.getResumeById(id);
        UserDto user = userService.getUserByEmail(authentication.getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        resume.setCreatedDateStr(resume.getCreatedDate().format(formatter));
        resume.setUpdateTimeStr(resume.getUpdateTime().format(formatter));

        model.addAttribute("resume", resume);
        model.addAttribute("isApplicant", user.getAuthority_id() == 1);

        return "resume/resumeInfo";
    }

    @GetMapping("/resume/create")
    public String create(Model model) {
        ResumeCreatingDto resumeCreatingDto = new ResumeCreatingDto();
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();

        model.addAttribute("resumeCreatingDto", resumeCreatingDto);
        model.addAttribute("categories", categories);
        model.addAttribute("contactTypes", contactTypes);
        return "resume/resumeCreate";
    }

    @PostMapping("/resume/create")
    public String create(@ModelAttribute ResumeCreatingDto resumeCreatingDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        ResumeDto resumeDto = resumeCreatingDto.getResumeDto();
        resumeDto.setApplicantId(user.getId().intValue());
        resumeDto.setEmail(user.getEmail());

        if (resumeDto.getCreatedDate() == null) {
            resumeDto.setCreatedDate(LocalDateTime.now());
        }
        if (resumeDto.getUpdateTime() == null) {
            resumeDto.setUpdateTime(LocalDateTime.now());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        resumeDto.setCreatedDateStr(resumeDto.getCreatedDate().format(formatter));
        resumeDto.setUpdateTimeStr(resumeDto.getUpdateTime().format(formatter));

        ResumeDto createdResume = resumeService.createFullResume(resumeCreatingDto);

        return "redirect:/resume/info/" + createdResume.getId();
    }
}