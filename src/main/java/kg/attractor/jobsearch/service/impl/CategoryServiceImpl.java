package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.errors.CanNotFindVacancyException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.repository.CategoryRepository;
import kg.attractor.jobsearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor


public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(this::mapToCategoryDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching categories: {}", e.getMessage());
            throw new CanNotFindVacancyException("Unable to fetch categories");
        }
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentId() != null ? category.getParentId().intValue() : null)
                .build();
    }
}
