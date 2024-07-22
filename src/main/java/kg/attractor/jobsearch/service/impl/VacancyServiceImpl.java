package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

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
}
