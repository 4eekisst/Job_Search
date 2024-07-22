package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class VacancyServiceImpl implements VacancyService {
    @Override
    public VacancyDto createVacancy(VacancyDto vacancyDto) {
        return VacancyDto.builder()
                .id(1L)
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .authorId(vacancyDto.getAuthorId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.getIsActive())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public VacancyDto editingVacancy(Long id, VacancyDto vacancyDto) {
        return VacancyDto.builder()
                .id(id)
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .authorId(vacancyDto.getAuthorId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.getIsActive())
                .createdDate(vacancyDto.getCreatedDate())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public void deleteVacancy(Long id) {
        log.info("Vacancy with id {} deleted", id);
    }

    @Override
    public List<UserDto> getApplicantsForVacancy(Long vacancyId) {
        return Arrays.asList(
                UserDto.builder()
                        .id(1L)
                        .name("Александр")
                        .surname("Крайнюков")
                        .age((byte) 27)
                        .email("alex@example.com")
                        .phoneNumber("+996-551-100-100")
                        .avatar("avatar1.jpg")
                        .accType("applicant")
                        .build(),
                UserDto.builder()
                        .id(2L)
                        .name("Рамазан")
                        .surname("Токошев")
                        .age((byte) 26)
                        .email("ramazan@example.com")
                        .phoneNumber("+995-552-200-200")
                        .avatar("avatar2.jpg")
                        .accType("applicant")
                        .build()
        );
    }
}
