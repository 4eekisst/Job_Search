package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    VacancyDto createVacancy(VacancyDto vacancyDto);
    VacancyDto editingVacancy(Long id, VacancyDto vacancyDto);
    void deleteVacancy(Long id);
    List<UserDto> getApplicantsForVacancy(Long vacancyId);
}
