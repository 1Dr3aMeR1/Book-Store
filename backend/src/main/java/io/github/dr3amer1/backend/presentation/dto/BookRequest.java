package io.github.dr3amer1.backend.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

    @DecimalMin("0.0")
    private BigDecimal price;

    private String previewUrl;

    private Integer publicationYear;
}