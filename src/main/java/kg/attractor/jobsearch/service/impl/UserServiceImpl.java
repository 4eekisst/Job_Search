package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ApplicantDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    @Override
    public UserDto getApplicantById(Long userId) {
        return UserDto.builder()
                .id(userId)
                .name("Илья")
                .surname("Беликов")
                .age((byte) 25)
                .email("ilia@example.com")
                .phoneNumber("+996-995-989-998")
                .avatar("avatar.jpg")
                .accType("applicant")
                .build();
    }

    @Override
    public UserDto getEmployerById(Long userId) {
        return UserDto.builder()
                .id(userId)
                .name("Jane Smith")
                .age((byte) 25)
                .email("jane@example.com")
                .phoneNumber("+996-551-111-222")
                .avatar("avatar.jpg")
                .accType("employer")
                .build();
    }


    @Override
    public ApplicantDto applyToVacancy(ApplicantDto applicationDto) {
        return ApplicantDto.builder()
                .id(1L)
                .vacancyId(applicationDto.getVacancyId())
                .applicantId(applicationDto.getApplicantId())
                .message(applicationDto.getMessage())
                .appliedDate(LocalDateTime.now())
                .isAccepted(false)
                .build();
    }
}
