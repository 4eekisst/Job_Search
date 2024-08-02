package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.errors.CanNotFindVacancyException;
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
        try {
            vacancyDao.saveVacancy(vacancyDto);
        } catch (Exception e) {
            log.error("An error occurred while creating vacancy: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to create vacancy");
        }
    }

    @Override
    public void updateVacancy(VacancyDto vacancyDto) {
        try {
            vacancyDao.updateVacancy(vacancyDto);
        } catch (Exception e) {
            log.error("An error occurred while updating vacancy: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to update vacancy");
        }
    }

    @Override
    public void deleteVacancy(Long id) {
        try {
            vacancyDao.deleteVacancy(id);
        } catch (Exception e) {
            log.error("An error occurred while deleting vacancy with ID {}: {}", id, e.getMessage());
            throw new CanNotFindVacancyException("Unable to delete vacancy with ID " + id);
        }
    }

    @Override
    public List<VacancyDto> getActiveVacancies() {
        try {
            return vacancyDao.findActiveVacancies();
        } catch (Exception e) {
            log.error("An error occurred while fetching active vacancies: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch active vacancies");
        }
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        try {
            List<VacancyDto> vacancies = vacancyDao.findVacanciesByCategory(categoryId);
            if (vacancies.isEmpty()) {
                throw new CanNotFindVacancyException("No vacancies found for category ID " + categoryId);
            }
            return vacancies;
        } catch (Exception e) {
            log.error("An error occurred while fetching vacancies by category ID {}: {}", categoryId, e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch vacancies by category ID " + categoryId);
        }
    }


    @Override
    public List<VacancyDto> getAllVacancies() {
        try {
            return vacancyDao.findAllVacancies();
        } catch (Exception e) {
            log.error("An error occurred while fetching all vacancies: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch all vacancies");
        }
    }
}
