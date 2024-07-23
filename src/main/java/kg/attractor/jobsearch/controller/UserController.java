package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ApplicantDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.ImageService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")

public class UserController {
    private final UserService userService;

    @GetMapping("/applicant/{userId}")
    public ResponseEntity<UserDto> getApplicantById(@PathVariable Long userId) {
        UserDto applicant = userService.getApplicantById(userId);
        return ResponseEntity.ok(applicant);
    }

    @GetMapping("/employer/{userId}")
    public ResponseEntity<UserDto> getEmployerById(@PathVariable Long userId) {
        UserDto employer = userService.getEmployerById(userId);
        return ResponseEntity.ok(employer);
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicantDto> applyToVacancy(@RequestBody ApplicantDto applicationDto) {
        ApplicantDto createdApplicationDto = userService.applyToVacancy(applicationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApplicationDto);
    }
}
