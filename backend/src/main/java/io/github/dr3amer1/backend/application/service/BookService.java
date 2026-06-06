package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.domain.model.BookEntity;
import io.github.dr3amer1.backend.domain.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Book not found: " + id));
    }

    public BookEntity createBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public BookEntity updateBook(Long id, BookEntity updatedBook) {

        BookEntity book = getBookById(id);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setDescription(updatedBook.getDescription());
        book.setPrice(updatedBook.getPrice());
        book.setPreviewUrl(updatedBook.getPreviewUrl());
        book.setPublicationYear(updatedBook.getPublicationYear());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}