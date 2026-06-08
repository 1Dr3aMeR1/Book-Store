package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.StoreEntity;
import io.github.dr3amer1.backend.presentation.dto.store.StoreResponse;

public class StoreMapper {

    private StoreMapper() {
    }

    public static StoreResponse toResponse(
            StoreEntity store
    ) {

        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}