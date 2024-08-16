package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
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

public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/vacancies")
    public String listVacancies(Model model) {
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies", vacancies);
        return "vacancy/vacancies";
    }

    @GetMapping("/vacancy/info/{id}")
    public String vacancyInfo(@PathVariable Long id, Model model) {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "vacancy/vacancyInfo";
    }
}
