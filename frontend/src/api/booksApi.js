import api from "./axios";

export const getBooks = () =>
    api.get("/books");

export const getBookById = (id) =>
    api.get(`/books/${id}`);

export const getBooksByCategory = (categoryId) =>
    api.get(`/books/category/${categoryId}`);

export const createBook = (book) =>
    api.post("/books", book);

export const updateBook = (id, book) =>
    api.put(`/books/${id}`, book);

export const deleteBook = (id) =>
    api.delete(`/books/${id}`);

export const addBookCategory = (bookId, categoryId) =>
    api.post(`/books/${bookId}/categories/${categoryId}`);

export const removeBookCategory = (bookId, categoryId) =>
    api.delete(`/books/${bookId}/categories/${categoryId}`);