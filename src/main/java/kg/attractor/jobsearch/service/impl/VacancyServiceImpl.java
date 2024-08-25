package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.errors.CanNotFindVacancyException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repository.CategoryRepository;
import kg.attractor.jobsearch.repository.UserRepository;
import kg.attractor.jobsearch.repository.VacancyRepository;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public VacancyDto createVacancy(VacancyDto vacancyDto) {
        try {
            if (vacancyDto.getIsActive() == null || !vacancyDto.getIsActive()) {
                throw new CanNotFindVacancyException("Vacancy must be active");
            }
            Vacancy vacancy = mapToVacancy(vacancyDto);
            vacancy.setCreatedDate(LocalDate.now());
            vacancy.setUpdateTime(LocalDate.now());
            Vacancy savedVacancy = vacancyRepository.save(vacancy);
            return mapToVacancyDto(savedVacancy);
        } catch (Exception e) {
            log.error("An error occurred while creating vacancy: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to create vacancy");
        }
    }

    @Override
    public void updateVacancy(VacancyDto vacancyDto) {
        try {
            Vacancy vacancy = vacancyRepository.findById(vacancyDto.getId())
                    .orElseThrow(() -> new CanNotFindVacancyException("Vacancy not found"));
            vacancy.setName(vacancyDto.getName());
            vacancy.setDescription(vacancyDto.getDescription());
            vacancy.setCategory(mapToCategory(vacancyDto.getCategoryId()));
            vacancy.setAuthor(mapToUser(vacancyDto.getAuthorId()));
            vacancy.setSalary(vacancyDto.getSalary());
            vacancy.setExpFrom(vacancyDto.getExpFrom());
            vacancy.setExpTo(vacancyDto.getExpTo());
            vacancy.setIsActive(vacancyDto.getIsActive());
            vacancy.setUpdateTime(LocalDate.now());
            vacancyRepository.save(vacancy);
        } catch (Exception e) {
            log.error("An error occurred while updating vacancy: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to update vacancy");
        }
    }

    @Override
    public void deleteVacancy(Long id) {
        try {
            if (!vacancyRepository.existsById(id)) {
                throw new CanNotFindVacancyException("Vacancy not found");
            }
            vacancyRepository.deleteById(id);
        } catch (Exception e) {
            log.error("An error occurred while deleting vacancy with ID {}: {}", id, e.getMessage());
            throw new CanNotFindVacancyException("Unable to delete vacancy with ID " + id);
        }
    }

    @Override
    public List<VacancyDto> getActiveVacancies() {
        try {
            List<Vacancy> vacancies = vacancyRepository.findAll().stream()
                    .filter(Vacancy::getIsActive)
                    .toList();
            return vacancies.stream()
                    .map(this::mapToVacancyDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching active vacancies: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch active vacancies");
        }
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        try {
            List<Vacancy> vacancies = vacancyRepository.findAll().stream()
                    .filter(vacancy -> vacancy.getCategory().getId().equals(categoryId))
                    .toList();
            if (vacancies.isEmpty()) {
                throw new CanNotFindVacancyException("No vacancies found for category ID " + categoryId);
            }
            return vacancies.stream()
                    .map(this::mapToVacancyDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching vacancies by category ID {}: {}", categoryId, e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch vacancies by category ID " + categoryId);
        }
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        try {
            List<Vacancy> vacancies = vacancyRepository.findAll();
            return vacancies.stream()
                    .map(this::mapToVacancyDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching all vacancies: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch all vacancies");
        }
    }

    @Override
    public VacancyDto getVacancyById(Long id) {
        try {
            Vacancy vacancy = vacancyRepository.findById(id)
                    .orElseThrow(() -> new CanNotFindVacancyException("Vacancy not found"));
            return mapToVacancyDto(vacancy);
        } catch (Exception e) {
            log.error("An error occurred while fetching vacancy with ID {}: {}", id, e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch vacancy with ID " + id);
        }
    }

    @Override
    public List<VacancyDto> getVacanciesByUserId(Long userId) {
        try {
            List<Vacancy> vacancies = vacancyRepository.findAll().stream()
                    .filter(vacancy -> vacancy.getAuthor().getId().equals(userId))
                    .toList();
            return vacancies.stream()
                    .map(this::mapToVacancyDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching vacancies by user ID {}: {}", userId, e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch vacancies by user ID " + userId);
        }
    }

    private VacancyDto mapToVacancyDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategory() != null ? vacancy.getCategory().getId() : null)
                .authorId(vacancy.getAuthor() != null ? vacancy.getAuthor().getId() : null)
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActive(vacancy.getIsActive())
                .createdDate(vacancy.getCreatedDate() != null ? vacancy.getCreatedDate().atStartOfDay() : null)
                .updateTime(vacancy.getUpdateTime() != null ? vacancy.getUpdateTime().atStartOfDay() : null)
                .build();
    }

    private Vacancy mapToVacancy(VacancyDto vacancyDto) {
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .category(mapToCategory(vacancyDto.getCategoryId()))
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.getIsActive())
                .createdDate(vacancyDto.getCreatedDate() != null ? vacancyDto.getCreatedDate().toLocalDate() : null)
                .updateTime(vacancyDto.getUpdateTime() != null ? vacancyDto.getUpdateTime().toLocalDate() : null)
                .author(mapToUser(vacancyDto.getAuthorId()))
                .build();
    }

    private Category mapToCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CanNotFindVacancyException("Category not found for ID " + categoryId));
    }

    private User mapToUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CanNotFindVacancyException("User not found for ID " + userId));
    }
}