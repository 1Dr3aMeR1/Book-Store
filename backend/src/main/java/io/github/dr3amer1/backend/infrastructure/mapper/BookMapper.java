package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.BookEntity;
import io.github.dr3amer1.backend.presentation.dto.book.BookRequest;
import io.github.dr3amer1.backend.presentation.dto.book.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponse toResponse(BookEntity book) {

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .price(book.getPrice())
                .previewUrl(book.getPreviewUrl())
                .publicationYear(book.getPublicationYear())
                .build();
    }

    public BookEntity toEntity(BookRequest request) {

        BookEntity book = new BookEntity();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setPrice(request.getPrice());
        book.setPreviewUrl(request.getPreviewUrl());
        book.setPublicationYear(request.getPublicationYear());

        return book;
    }
}
