package io.github.dr3amer1.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String description;

    private BigDecimal price;

    @Column(name = "preview_url")
    private String previewUrl;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns =
            @JoinColumn(name = "book_id"),

            inverseJoinColumns =
            @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories =
            new HashSet<>();
}