package kg.attractor.jobsearch.controller;

import jakarta.servlet.http.HttpSession;
import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.dto.customDto.ResumeCreatingDto;
import kg.attractor.jobsearch.dto.customDto.ResumeEditDto;
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
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;

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
    public String resumeInfo(@PathVariable Long id, Model model, Authentication authentication, HttpSession session) {
        ResumeDto resume = resumeService.getResumeById(id);
        UserDto user = userService.getUserByEmail(authentication.getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        resume.setCreatedDateStr(resume.getCreatedDate().format(formatter));
        resume.setUpdateTimeStr(resume.getUpdateTime().format(formatter));

        boolean isApplicant = user.getAuthorityId() == 1;

        Boolean fromEditOrCreate = (Boolean) session.getAttribute("fromEditOrCreate");
        session.removeAttribute("fromEditOrCreate");

        model.addAttribute("resume", resume);
        model.addAttribute("isApplicant", isApplicant);
        model.addAttribute("fromEditOrCreate", fromEditOrCreate != null ? fromEditOrCreate : false);

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
    public String create(@ModelAttribute ResumeCreatingDto resumeCreatingDto, HttpSession session) {
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

        session.setAttribute("fromEditOrCreate", true);

        return "redirect:/resume/info/" + createdResume.getId();
    }

    @GetMapping("/resume/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ResumeDto resume = resumeService.getResumeById(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<ContactTypeDto> contactTypes = contactTypeService.getAllContactTypes();

        List<WorkExperienceInfoDto> workExperienceInfoDtos = workExperienceInfoService.getWorkExperiencesByResumeId(id);
        List<EducationInfoDto> educationInfoDtos = educationInfoService.getEducationByResumeId(id);
        List<ContactInfoDto> contactInfoDtos = contactInfoService.getContactsByResumeId(id);

        model.addAttribute("resumeDto", resume);
        model.addAttribute("categories", categories);
        model.addAttribute("contactTypes", contactTypes);
        model.addAttribute("workExperienceInfoDtos", workExperienceInfoDtos);
        model.addAttribute("educationInfoDtos", educationInfoDtos);
        model.addAttribute("contactInfoDtos", contactInfoDtos);

        return "resume/resumeEdit";
    }

    @PostMapping("/resume/edit")
    public String update(@ModelAttribute ResumeEditDto resumeEditDto, Model model) {
        ResumeDto existingResume = resumeService.getResumeById(resumeEditDto.getResumeDto().getId());

        if (resumeEditDto.getResumeDto().getIsActive() == null) {
            resumeEditDto.getResumeDto().setIsActive(false);
        }

        resumeEditDto.getResumeDto().setName(existingResume.getName());
        resumeEditDto.getResumeDto().setSalary(existingResume.getSalary());
        resumeEditDto.getResumeDto().setCreatedDate(existingResume.getCreatedDate());
        resumeEditDto.getResumeDto().setUpdateTime(LocalDateTime.now());
        resumeEditDto.getResumeDto().setCategoryId(resumeEditDto.getResumeDto().getCategoryId());

        resumeService.updateFullResume(resumeEditDto);

        return "redirect:/resume/info/" + resumeEditDto.getResumeDto().getId();
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