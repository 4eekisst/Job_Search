package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.CategoryDao;
import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
import kg.attractor.jobsearch.errors.CanNotFindVacancyException;
import kg.attractor.jobsearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor


public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            return categoryDao.findAllCategories();
        } catch (Exception e) {
            log.error("An error occurred while fetching categories: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch categories");
        }
    }
}
