import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { getBooks } from "../../api/booksApi";
import { getCategories } from "../../api/categoriesApi";
import { useCart } from "../../context/CartContext";

const Catalog = () => {
    const [books, setBooks] = useState([]);
    const [categories, setCategories] = useState([]);
    const [showFilter, setShowFilter] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [minPrice, setMinPrice] = useState("");
    const [maxPrice, setMaxPrice] = useState("");
    const { addToCart } = useCart();

    useEffect(() => {
        getBooks()
            .then(response => {
                setBooks(response.data);
            })
            .catch(error => {
                console.log(error);
            });

        getCategories()
            .then(response => {
                setCategories(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const filterBooks = () => {
        let result = books;
        if (selectedCategory) {
            result = result.filter(book =>
                book.categories.includes(selectedCategory)
            );
        }

        if (minPrice) {
            result = result.filter(book =>
                book.price >= Number(minPrice)
            );
        }



        if (maxPrice) {
            result = result.filter(book =>
                book.price <= Number(maxPrice)
            );
        }
        return result;
    };

    return (
        <div>
            <div className="catalog-header">
                <h1>
                    Каталог книг
                </h1>

                <button
                    className="filter-button"
                    onClick={() =>
                        setShowFilter(!showFilter)
                    }
                >
                    Фильтр
                </button>
            </div>

            {
                showFilter &&
                <div className="filter-panel">
                    <h3>
                        Жанры
                    </h3>

                    {
                        categories.map(category => (
                            <button
                                key={category.id}
                                onClick={() =>
                                    setSelectedCategory(category.name)
                                }
                            >
                                {category.name}

                            </button>
                        ))
                    }

                    <h3>
                        Цена
                    </h3>

                    <input
                        placeholder="От"
                        value={minPrice}
                        onChange={(e) =>
                            setMinPrice(e.target.value)
                        }
                    />

                    <input
                        placeholder="До"
                        value={maxPrice}
                        onChange={(e) =>
                            setMaxPrice(e.target.value)
                        }
                    />

                    <button
                        onClick={() => {
                            setSelectedCategory(null);
                            setMinPrice("");
                            setMaxPrice("");
                        }}
                    >
                        Сбросить
                    </button>
                </div>
            }

            <div className="books-list">
                {
                    filterBooks().map(book => (
                        <div
                            className="book-card"
                            key={book.id}
                        >
                            {
                                book.previewUrl &&
                                <img
                                    className="book-image"
                                    src={book.previewUrl}
                                    alt={book.title}
                                />
                            }

                            <h2>
                                {book.title}
                            </h2>

                            <p className="book-author">
                                Автор: {book.author}
                            </p>

                            <p className="book-price">
                                {book.price} ₽
                            </p>

                            <Link
                                className="details-button"
                                to={`/books/${book.id}`}
                            >
                                Подробнее
                            </Link>

                            <button
                                className="cart-button"
                                onClick={() =>
                                    addToCart(book)
                                }
                            >
                                В корзину
                            </button>
                        </div>
                    ))
                }
            </div>
        </div>
    );
};

export default Catalog;