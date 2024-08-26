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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public String listResumes(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ResumeDto> resumesPage = resumeService.getAllResumes(pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (ResumeDto resume : resumesPage.getContent()) {
            resume.setCreatedDateStr(resume.getCreatedDate().format(formatter));
        }

        model.addAttribute("resumesPage", resumesPage);
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
        model.addAttribute("isApplicant", user.getAuthorityId() == 1);

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

    @GetMapping("/resume/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ResumeDto resume = resumeService.getResumeById(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();

        model.addAttribute("resumeDto", resume);
        model.addAttribute("categories", categories);
        model.addAttribute("contactTypes", contactTypes);
        return "resume/resumeEdit";
    }

    @PostMapping("/resume/edit")
    public String update(@ModelAttribute ResumeDto resumeDto) {
        ResumeDto existingResume = resumeService.getResumeById(resumeDto.getId());

        resumeDto.setCreatedDate(existingResume.getCreatedDate());
        resumeDto.setUpdateTime(LocalDateTime.now());

        resumeService.updateResume(resumeDto);

        return "redirect:/resume/info/" + resumeDto.getId();
    }

    @PostMapping("/resume/update-time")
    public String updateResumeTime(@RequestParam Long id) {
        ResumeDto resumeDto = resumeService.getResumeById(id);
        if (resumeDto != null) {
            resumeDto.setUpdateTime(LocalDateTime.now());
            resumeService.updateResume(resumeDto);
        }
        return "redirect:/profile";
    }
}