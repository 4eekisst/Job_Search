package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
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
    public String listVacancies(Model model) {
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies", vacancies);
        return "vacancy/vacancies";
    }

    @GetMapping("/vacancy/info/{id}")
    public String vacancyInfo(@PathVariable Long id, Model model, Authentication authentication) {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        UserDto user = userService.getUserByEmail(authentication.getName());

        model.addAttribute("vacancy", vacancy);
        model.addAttribute("isEmployer", user.getAuthorityId() == 2);
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
    public String create(@ModelAttribute VacancyDto vacancyDto, Model model) {
        if (vacancyDto.getIsActive() == null || !vacancyDto.getIsActive()) {
            model.addAttribute("error", "Поставьте галочку в конце формы, чтобы создать вакансию.");
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("vacancy", vacancyDto);
            return "vacancy/vacancyCreate";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();

        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());
        vacancyDto.setAuthorId(userId);

        VacancyDto createdVacancy = vacancyService.createVacancy(vacancyDto);
        return "redirect:/vacancy/info/" + createdVacancy.getId();
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
    public String edit(@ModelAttribute VacancyDto vacancyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();

        vacancyDto.setUpdateTime(LocalDateTime.now());
        vacancyDto.setAuthorId(userId);

        vacancyService.updateVacancy(vacancyDto);
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
