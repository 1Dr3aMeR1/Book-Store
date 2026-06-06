package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.BookService;
import io.github.dr3amer1.backend.domain.model.BookEntity;
import io.github.dr3amer1.backend.infrastructure.mapper.BookMapper;
import io.github.dr3amer1.backend.presentation.dto.BookRequest;
import io.github.dr3amer1.backend.presentation.dto.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponse> getAllBooks() {

        return bookService.getAllBooks()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(
            @PathVariable Long id) {

        return bookMapper.toResponse(
                bookService.getBookById(id)
        );
    }

    @PostMapping
    public BookResponse createBook(
            @Valid
            @RequestBody BookRequest request) {

        BookEntity created =
                bookService.createBook(
                        bookMapper.toEntity(request));

        return bookMapper.toResponse(created);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(
            @PathVariable Long id) {

        bookService.deleteBook(id);
    }
}
