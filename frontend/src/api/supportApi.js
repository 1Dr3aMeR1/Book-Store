import api from "./axios";

export const createTicket = (data) =>
    api.post("/support", data);

export const getMyTickets = () =>
    api.get("/support/my");

export const getAllTickets = () =>
    api.get("/support");

export const getMessages = (ticketId) =>
    api.get(`/support/${ticketId}/messages`);

export const sendMessage = (ticketId, data) =>
    api.post(`/support/${ticketId}/messages`, data);

export const updateTicketStatus = (ticketId, status) =>
    api.patch(
        `/support/${ticketId}/status`,
        null,
        {
            params: {
                status: status
            }
        }
    );