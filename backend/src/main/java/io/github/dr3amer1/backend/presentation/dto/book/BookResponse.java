package io.github.dr3amer1.backend.presentation.dto.book;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private String description;

    private BigDecimal price;

    private String previewUrl;

    private Integer publicationYear;
}