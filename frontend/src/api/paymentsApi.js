import api from "./axios";

export const createPayment = (payment) =>
    api.post("/payments", payment);

export const getPayment = (id) =>
    api.get(`/payments/${id}`);