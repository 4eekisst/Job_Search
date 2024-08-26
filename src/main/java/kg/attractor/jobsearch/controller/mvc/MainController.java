package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;



@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class MainController {
    private final VacancyService vacancyService;

    @GetMapping
    public String getAllVacancies(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
        Page<VacancyDto> vacanciesPage = vacancyService.getAllVacancies(page, size);
        model.addAttribute("vacanciesPage", vacanciesPage);
        return "main/index";
    }
}
