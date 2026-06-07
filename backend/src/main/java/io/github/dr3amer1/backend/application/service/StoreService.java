package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import io.github.dr3amer1.backend.domain.model.StoreEntity;
import io.github.dr3amer1.backend.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<StoreEntity> getAllStores() {

        return storeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StoreEntity getStoreById(Long id) {

        return storeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Store not found: " + id
                        ));
    }

    public StoreEntity createStore(
            StoreEntity store) {

        return storeRepository.save(store);
    }

    public StoreEntity updateStore(
            Long id,
            StoreEntity updatedStore) {

        StoreEntity store =
                getStoreById(id);

        store.setName(
                updatedStore.getName());

        store.setAddress(
                updatedStore.getAddress());

        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {

        StoreEntity store =
                getStoreById(id);

        storeRepository.delete(store);
    }
}