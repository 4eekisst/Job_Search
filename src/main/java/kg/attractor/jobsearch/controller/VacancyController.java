package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancies")


public class VacancyController {
    private final VacancyService service;

    @PostMapping("/create")
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) {
        VacancyDto createdVacancyDto = service.createVacancy(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVacancyDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Long id, @RequestBody VacancyDto vacancyDto) {
        VacancyDto updatedVacancyDto = service.editingVacancy(id, vacancyDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVacancyDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        service.deleteVacancy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
