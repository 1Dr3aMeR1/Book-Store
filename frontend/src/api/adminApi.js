import api from "./axios";

export const addBook = (book) => {
    return api.post(
        "/books", book
    );
};

export const updateBook = (id, book) => {
    return api.put(
        `/books/${id}`, book
    );
};

export const deleteBook = (id) => {
    return api.delete(
        `/books/${id}`
    );
};