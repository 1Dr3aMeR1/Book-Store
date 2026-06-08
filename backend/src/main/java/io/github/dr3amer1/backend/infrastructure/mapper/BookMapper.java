package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.BookEntity;
import io.github.dr3amer1.backend.domain.model.CategoryEntity;
import io.github.dr3amer1.backend.presentation.dto.book.BookRequest;
import io.github.dr3amer1.backend.presentation.dto.book.BookResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper {

    private BookMapper() {
    }

    public BookResponse toResponse(
            BookEntity book
    ) {

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .price(book.getPrice())
                .previewUrl(book.getPreviewUrl())
                .publicationYear(book.getPublicationYear())
                .categories(
                        book.getCategories()
                                .stream()
                                .map(CategoryEntity::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public BookEntity toEntity(BookRequest request) {

        BookEntity book = new BookEntity();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        book.setPrice(request.getPrice());
        book.setPreviewUrl(request.getPreviewUrl());
        book.setPublicationYear(request.getPublicationYear());

        return book;
    }
}
