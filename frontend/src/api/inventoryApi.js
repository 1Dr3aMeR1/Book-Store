import api from "./axios";

export const getBookInventory = (bookId) =>
    api.get(`/inventory/book/${bookId}`);

export const createInventory = (data) =>
    api.post("/inventory", data);

export const updateInventory = (id, data) =>
    api.put(`/inventory/${id}`, data);