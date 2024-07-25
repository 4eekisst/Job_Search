package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("respondedApplicant")

public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;

    @PostMapping("/respond")
    public ResponseEntity<String> respondToVacancy(@RequestBody RespondedApplicantDto respondedApplicantDto) {
        respondedApplicantService.respondToVacancy(respondedApplicantDto);
        return ResponseEntity.ok("Response recorded successfully");
    }

    @GetMapping("/vacancy/{vacancyId}")
    public ResponseEntity<List<RespondedApplicantDto>> getApplicantsForVacancy(@PathVariable Long vacancyId) {
        List<RespondedApplicantDto> applicants = respondedApplicantService.getApplicantsForVacancy(vacancyId);
        return ResponseEntity.ok(applicants);
    }
}
