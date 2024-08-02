package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")

public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search/byName")
    public ResponseEntity<List<UserDto>> getUsersByName(@RequestParam("name") String name) {
        List<UserDto> users = userService.getUsersByName(name);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/byPhone")
    public ResponseEntity<List<UserDto>> getUsersByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        List<UserDto> users = userService.getUsersByPhoneNumber(phoneNumber);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/byEmail")
    public ResponseEntity<List<UserDto>> getUsersByEmail(@RequestParam("email") String email) {
        List<UserDto> users = userService.getUsersByEmail(email);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/employer/{id}")
    public ResponseEntity<UserDto> getEmployerById(@PathVariable("id") Long userId) {
        UserDto employer = userService.getEmployerById(userId);
        if (employer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employer);
    }

    @GetMapping("/applicant/{id}")
    public ResponseEntity<UserDto> getApplicantById(@PathVariable("id") Long userId) {
        UserDto applicant = userService.getApplicantById(userId);
        if (applicant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(applicant);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> checkUserExistsByEmail(@PathVariable String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserProfile(@PathVariable("id") Long userId, @RequestBody @Valid UserDto userDto) {
        UserDto updatedUser = userService.updateUserProfile(userId, userDto);
        if (updatedUser ==  null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping ("/create")
    public void addUser(@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);
    }
}
