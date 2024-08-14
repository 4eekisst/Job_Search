package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("respondedApplicantRest")
@RequiredArgsConstructor
@RequestMapping("api/respondedApplicant")

public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;

    @PostMapping("/respond")
    public ResponseEntity<String> respondToVacancy(@RequestBody @Valid RespondedApplicantDto respondedApplicantDto) {
        respondedApplicantService.respondToVacancy(respondedApplicantDto);
        return ResponseEntity.ok("Response recorded successfully");
    }
    @GetMapping("/vacancy/{vacancyId}")
    public ResponseEntity<List<RespondedApplicantDto>> getApplicantsForVacancy(@PathVariable @Positive(message = "Vacancy ID must be positive") Long vacancyId) {
        List<RespondedApplicantDto> applicants = respondedApplicantService.getApplicantsForVacancy(vacancyId);
        return ResponseEntity.ok(applicants);
    }

    @GetMapping("/vacancies/{applicantId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByApplicantId(@PathVariable @Positive(message = "Applicant ID must be positive") Integer applicantId) {
        List<VacancyDto> vacancies = respondedApplicantService.getVacanciesByApplicantId(applicantId);
        return ResponseEntity.ok(vacancies);
    }
}
