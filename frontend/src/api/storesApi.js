import api from "./axios";

export const getStores = () =>
    api.get("/stores");

export const getStoreById = (id) =>
    api.get(`/stores/${id}`);

export const createStore = (store) =>
    api.post("/stores", store);

export const updateStore = (id, store) =>
    api.put(`/stores/${id}`, store);

export const deleteStore = (id) =>
    api.delete(`/stores/${id}`);