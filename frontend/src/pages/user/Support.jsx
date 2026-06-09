import { useEffect, useState } from "react";
import {
    createTicket,
    getMyTickets,
    getMessages,
    sendMessage
} from "../../api/supportApi";

const Support = () => {
    const [tickets, setTickets] = useState([]);
    const [selectedTicket, setSelectedTicket] = useState(null);
    const [messages, setMessages] = useState([]);
    const [subject, setSubject] = useState("");
    const [message, setMessage] = useState("");
    const loadTickets = () => {
        getMyTickets()
            .then(response => {
                setTickets(response.data);
            });
    };

    useEffect(() => {
        loadTickets();
    }, []);

    const create = () => {
        createTicket({
            subject: subject
        })
            .then(() => {
                setSubject("");
                loadTickets();
            });
    };

    const open = (ticket) => {
        setSelectedTicket(ticket);
        getMessages(ticket.id)
            .then(response => {
                setMessages(response.data);
            });
    };

    const send = () => {
        sendMessage(
            selectedTicket.id,
            {
                message: message
            }
        )
            .then(() => {
                setMessage("");
                open(selectedTicket);
            });
    };

    return (
        <div>
            <h1>
                Поддержка
            </h1>

            <div className="support-send">
                <input
                    placeholder="Тема обращения"
                    value={subject}
                    onChange={(e) =>
                        setSubject(e.target.value)
                    }
                />

                <button onClick={create}>
                    Создать
                </button>
            </div>

            <h2>
                Мои обращения
            </h2>

            <div className="support-list">{
                    tickets.map(ticket => (
                        <div
                            className="support-ticket"
                            key={ticket.id}
                            onClick={() => open(ticket)
                            }
                        >
                            <h3>
                                {ticket.subject}
                            </h3>

                            <p>
                                Статус: {ticket.status}
                            </p>
                        </div>
                    ))
                }
            </div>

            {
                selectedTicket &&
                <div className="support-chat">
                    <h2>
                        {selectedTicket.subject}
                    </h2>
                    <h3>
                        Сообщения
                    </h3>

                    <div className="messages">
                        {
                            messages.map(item => (

                                <div
                                    className="message"
                                    key={item.id}
                                >
                                    {item.message}
                                </div>
                            ))
                        }
                    </div>
                    <div className="support-send">
                        <input
                            placeholder="Сообщение"
                            value={message}
                            onChange={(e) =>
                                setMessage(e.target.value)
                            }
                        />

                        <button onClick={send}>
                            Отправить
                        </button>
                    </div>
                </div>
            }
        </div>
    );
};

export default Support;