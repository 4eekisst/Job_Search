package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
