package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VacancyService {
    VacancyDto createVacancy(VacancyDto vacancyDto);
    void updateVacancy(VacancyDto vacancyDto);
    void deleteVacancy(Long id);
    List<VacancyDto> getActiveVacancies();
    List<VacancyDto> getVacanciesByCategory(Long categoryId);
    @Transactional(readOnly = true)
    Page<VacancyDto> getAllVacancies(int page, int size, String sortBy);
    VacancyDto getVacancyById(Long id);
    Page<VacancyDto> getVacanciesByUserId(Long userId, int page, int size);
}
