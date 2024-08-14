package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class HomeController {
    private final VacancyService vacancyService;

    @GetMapping
    public String getAllVacancies(Model model) {
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies", vacancies);
        return "home/index";
    }
}
