import api from "./axios";

export const getOrders = () =>
    api.get("/orders");

export const getOrderById = (id) =>
    api.get(`/orders/${id}`);

export const getMyOrders = () =>
    api.get("/orders/my");

export const createOrder = (order) =>
    api.post("/orders", order);