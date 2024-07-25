package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancies")


public class VacancyController {
    private final VacancyService vacancyService;

    @PostMapping("/create")
    public ResponseEntity<String> createVacancy(@RequestBody VacancyDto vacancyDto) {
        vacancyService.createVacancy(vacancyDto);
        return ResponseEntity.ok("Vacancy created successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateVacancy(@RequestBody VacancyDto vacancyDto) {
        vacancyService.updateVacancy(vacancyDto);
        return ResponseEntity.ok("Vacancy updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.ok("Vacancy deleted successfully");
    }

    @GetMapping("/active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        List<VacancyDto> vacancies = vacancyService.getActiveVacancies();
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable Long categoryId) {
        List<VacancyDto> vacancies = vacancyService.getVacanciesByCategory(categoryId);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        return ResponseEntity.ok(vacancies);
    }
}
