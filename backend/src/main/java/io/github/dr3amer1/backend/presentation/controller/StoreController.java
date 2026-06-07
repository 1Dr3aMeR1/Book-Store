package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.StoreService;
import io.github.dr3amer1.backend.domain.model.StoreEntity;
import io.github.dr3amer1.backend.presentation.dto.book.BookRequest;
import io.github.dr3amer1.backend.presentation.dto.store.StoreRequest;
import io.github.dr3amer1.backend.presentation.dto.store.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<StoreResponse> getAllStores() {

        return storeService.getAllStores()
                .stream()
                .map(store -> StoreResponse.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .address(store.getAddress())
                        .build())
                .toList();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StoreResponse createStore(
            @RequestBody StoreRequest request) {

        StoreEntity store =
                new StoreEntity();

        store.setName(request.getName());
        store.setAddress(request.getAddress());

        StoreEntity saved =
                storeService.createStore(store);

        return StoreResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStore(
            @PathVariable Long id) {

        storeService.deleteStore(id);
    }
}