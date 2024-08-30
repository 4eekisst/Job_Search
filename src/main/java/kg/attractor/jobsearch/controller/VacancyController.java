package kg.attractor.jobsearch.controller;

import jakarta.servlet.http.HttpSession;
import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/vacancies")
    public String listVacancies(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "date") String sortBy,
                                @RequestParam(required = false) String previousSortBy,
                                Model model) {
        if (previousSortBy != null && !previousSortBy.equals(sortBy)) {
            page = 0;
        }

        Page<VacancyDto> vacanciesPage = vacancyService.getAllVacancies(page, size, sortBy);
        model.addAttribute("vacanciesPage", vacanciesPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("previousSortBy", sortBy);
        return "vacancy/vacancies";
    }

    @GetMapping("/vacancy/info/{id}")
    public String vacancyInfo(@PathVariable Long id, Model model, Authentication authentication, HttpSession session) {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        boolean isEmployer = false;

        if (authentication != null) {
            UserDto user = userService.getUserByEmail(authentication.getName());
            isEmployer = user.getAuthorityId() == 2;
        }

        Boolean fromEditOrCreate = (Boolean) session.getAttribute("fromEditOrCreate");
        session.removeAttribute("fromEditOrCreate");

        model.addAttribute("vacancy", vacancy);
        model.addAttribute("isEmployer", isEmployer);
        model.addAttribute("fromEditOrCreate", fromEditOrCreate != null ? fromEditOrCreate : false);

        return "vacancy/vacancyInfo";
    }

    @GetMapping("/vacancy/create")
    public String create(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("vacancy", new VacancyDto());
        model.addAttribute("categories", categories);
        return "vacancy/vacancyCreate";
    }

    @PostMapping("/vacancy/create")
    public String create(@ModelAttribute VacancyDto vacancyDto, Model model, HttpSession session) {
        if (vacancyDto.getIsActive() == null || !vacancyDto.getIsActive()) {
            model.addAttribute("error", "Поставьте галочку в конце формы, чтобы создать вакансию.");
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("vacancy", vacancyDto);
            return "vacancy/vacancyCreate";
        }

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            UserDto user = userService.getUserByEmail(email);
            Long userId = user.getId();

            vacancyDto.setCreatedDate(LocalDateTime.now());
            vacancyDto.setUpdateTime(LocalDateTime.now());
            vacancyDto.setAuthorId(userId);

            VacancyDto createdVacancy = vacancyService.createVacancy(vacancyDto);
            session.setAttribute("fromEditOrCreate", true);
            return "redirect:/vacancy/info/" + createdVacancy.getId();
        } catch (IllegalArgumentException e) {
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("vacancy", vacancyDto);
            return "vacancy/vacancyCreate";
        }
    }

    @GetMapping("/vacancy/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("categories", categories);
        return "vacancy/vacancyEdit";
    }

    @PostMapping("/vacancy/edit")
    public String edit(@ModelAttribute VacancyDto vacancyDto, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();

        vacancyDto.setUpdateTime(LocalDateTime.now());
        vacancyDto.setAuthorId(userId);

        vacancyService.updateVacancy(vacancyDto);
        session.setAttribute("fromEditOrCreate", true);
        return "redirect:/vacancy/info/" + vacancyDto.getId();
    }

    @PostMapping("/vacancy/update-time")
    public String updateTime(@RequestParam Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();

        VacancyDto vacancyDto = vacancyService.getVacancyById(id);
        if (vacancyDto != null) {
            vacancyDto.setUpdateTime(LocalDateTime.now());
            vacancyDto.setAuthorId(userId);
            vacancyService.updateVacancy(vacancyDto);
        }

        return "redirect:/profile";
    }
}
