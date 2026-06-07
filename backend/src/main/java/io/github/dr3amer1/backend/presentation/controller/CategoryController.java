package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.CategoryService;
import io.github.dr3amer1.backend.domain.model.CategoryEntity;
import io.github.dr3amer1.backend.presentation.dto.category.CategoryRequest;
import io.github.dr3amer1.backend.presentation.dto.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<CategoryResponse> getAllCategories() {

        return categoryService.getAllCategories()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CategoryResponse getCategory(
            @PathVariable Long id
    ) {

        return toResponse(
                categoryService.getCategoryById(id)
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(
            @RequestBody CategoryRequest request
    ) {

        CategoryEntity category =
                new CategoryEntity();

        category.setName(
                request.getName()
        );

        return toResponse(
                categoryService.createCategory(
                        category
                )
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequest request
    ) {

        CategoryEntity category =
                new CategoryEntity();

        category.setName(
                request.getName()
        );

        return toResponse(
                categoryService.updateCategory(
                        id,
                        category
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(
            @PathVariable Long id
    ) {

        categoryService.deleteCategory(id);
    }

    private CategoryResponse toResponse(
            CategoryEntity category
    ) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}