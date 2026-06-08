package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.CategoryEntity;
import io.github.dr3amer1.backend.presentation.dto.category.CategoryResponse;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryResponse toResponse(
            CategoryEntity category
    ) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}