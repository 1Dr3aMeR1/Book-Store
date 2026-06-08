import api from "./axios";

export const getBooks = () => {
    return api.get("/books");
};

export const getBookById = (id) => {
    return api.get(
        `/books/${id}`
    );
};

export const getBookAvailability = (id) => {
    return api.get(
        `/inventory/book/${id}`
    );
};