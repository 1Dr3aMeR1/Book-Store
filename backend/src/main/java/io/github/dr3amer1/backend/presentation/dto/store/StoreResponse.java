package io.github.dr3amer1.backend.presentation.dto.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {

    private Long id;

    private String name;

    private String address;
}
