package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    void createVacancy(VacancyDto vacancyDto);
    void updateVacancy(VacancyDto vacancyDto);
    void deleteVacancy(Long id);
    List<VacancyDto> getActiveVacancies();
    List<VacancyDto> getVacanciesByCategory(Long categoryId);
    List<VacancyDto> getAllVacancies();
    VacancyDto getVacancyById(Long id);
    List<VacancyDto> getVacanciesByUserId(Long userId);
}
