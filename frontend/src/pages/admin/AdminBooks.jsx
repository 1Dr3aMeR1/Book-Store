import { useEffect, useState } from "react";

import {
    getBooks,
    createBook,
    updateBook,
    deleteBook
} from "../../api/booksApi";

const AdminBooks = () => {
    const [books, setBooks] = useState([]);
    const [form, setForm] = useState({
        title: "",
        author: "",
        isbn: "",
        description: "",
        price: "",
        previewUrl: "",
        publicationYear: ""
    });


    const [editId, setEditId] = useState(null);
    const loadBooks = () => {
        getBooks()
            .then(response => {

                setBooks(response.data);

            });

    };

    useEffect(() => {
        loadBooks();
    }, []);

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const saveBook = () => {
        if (editId) {
            updateBook(
                editId,
                form
            )
                .then(() => {
                    setEditId(null);
                    clearForm();
                    loadBooks();
                });
        }

        else {
            createBook(form)
                .then(() => {
                    clearForm();
                    loadBooks();
                });
        }
    };


    const editBook = (book) => {
        setEditId(book.id);
        setForm({
            title: book.title,
            author: book.author,
            isbn: book.isbn,
            description: book.description,
            price: book.price,
            previewUrl: book.previewUrl,
            publicationYear: book.publicationYear
        });
    };

    const removeBook = (id) => {
        deleteBook(id)
            .then(() => {
                loadBooks();
            });
    };


    const clearForm = () => {
        setForm({
            title: "",
            author: "",
            isbn: "",
            description: "",
            price: "",
            previewUrl: "",
            publicationYear: ""
        });
    };





    return (
        <div className="admin-page">
            <h1>
                Управление книгами
            </h1>


            <div className="admin-form">

                <input
                    name="title"
                    placeholder="Название"
                    value={form.title}
                    onChange={handleChange}
                />

                <input
                    name="author"
                    placeholder="Автор"
                    value={form.author}
                    onChange={handleChange}
                />

                <input
                    name="isbn"
                    placeholder="ISBN"
                    value={form.isbn}
                    onChange={handleChange}
                />

                <input
                    name="description"
                    placeholder="Описание"
                    value={form.description}
                    onChange={handleChange}
                />

                <input
                    name="price"
                    placeholder="Цена"
                    value={form.price}
                    onChange={handleChange}
                />

                <input
                    name="previewUrl"
                    placeholder="Картинка"
                    value={form.previewUrl}
                    onChange={handleChange}
                />

                <input
                    name="publicationYear"
                    placeholder="Год"
                    value={form.publicationYear}
                    onChange={handleChange}
                />

                <button onClick={saveBook}>
                    {
                        editId ?
                            "Сохранить"
                            :
                            "Добавить"
                    }
                </button>
            </div>

            {
                books.map(book => (
                    <div
                        className="support-ticket"
                        key={book.id}
                    >
                        <div className="admin-card-title">
                            {book.title}
                        </div>

                        <div className="admin-card-text">
                            Автор: {book.author}
                            <br/>
                            Цена: {book.price} ₽
                        </div>

                        <div className="admin-actions">
                            <button
                                onClick={() =>
                                    editBook(book)
                                }
                            >
                                Изменить
                            </button>

                            <button
                                onClick={() =>
                                    removeBook(book.id)
                                }
                            >
                                Удалить
                            </button>
                        </div>
                    </div>
                ))
            }
        </div>
    );
};

export default AdminBooks;