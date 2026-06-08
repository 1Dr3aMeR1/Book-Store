import api from "./axios";

export const createOrder = (order) => {
    return api.post(
        "/orders", order
    );
};

export const getMyOrders = () => {
    return api.get(
        "/orders/my"
    );
};