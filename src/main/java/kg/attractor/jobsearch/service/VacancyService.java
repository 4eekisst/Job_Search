package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;

public interface VacancyService {
    VacancyDto createVacancy(VacancyDto vacancyDto);
    VacancyDto editingVacancy(Long id, VacancyDto vacancyDto);
    void deleteVacancy(Long id);
}
