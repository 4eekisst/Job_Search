package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    @Override
    public void createVacancy(VacancyDto vacancyDto) {
        vacancyDao.saveVacancy(vacancyDto);
    }

    @Override
    public void updateVacancy(VacancyDto vacancyDto) {
        vacancyDao.updateVacancy(vacancyDto);
    }

    @Override
    public void deleteVacancy(Long id) {
        vacancyDao.deleteVacancy(id);
    }

    @Override
    public List<VacancyDto> getActiveVacancies() {
        return vacancyDao.findActiveVacancies();
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        return vacancyDao.findVacanciesByCategory(categoryId);
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        return vacancyDao.findAllVacancies();
    }
}
