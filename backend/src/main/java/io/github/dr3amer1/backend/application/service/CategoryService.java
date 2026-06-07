package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import io.github.dr3amer1.backend.domain.model.CategoryEntity;
import io.github.dr3amer1.backend.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryEntity> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryEntity getCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category not found: " + id
                        ));
    }

    public CategoryEntity createCategory(
            CategoryEntity category
    ) {

        return categoryRepository.save(category);
    }

    public CategoryEntity updateCategory(
            Long id,
            CategoryEntity updatedCategory
    ) {

        CategoryEntity category =
                getCategoryById(id);

        category.setName(
                updatedCategory.getName()
        );

        return categoryRepository.save(
                category
        );
    }

    public void deleteCategory(Long id) {

        CategoryEntity category =
                getCategoryById(id);

        categoryRepository.delete(category);
    }
}