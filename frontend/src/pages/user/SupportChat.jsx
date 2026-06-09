import { useEffect, useState } from "react";


import {
    getAllTickets,
    getMessages,
    sendMessage,
    updateTicketStatus
} from "../../api/supportApi";



const AdminSupport = () => {


    const [tickets, setTickets] = useState([]);

    const [selectedTicket, setSelectedTicket] = useState(null);

    const [messages, setMessages] = useState([]);

    const [message, setMessage] = useState("");



    const loadTickets = () => {


        getAllTickets()

            .then(response => {

                setTickets(response.data);

            })

            .catch(error => {

                console.log(error);

            });


    };



    useEffect(() => {


        loadTickets();


    }, []);




    const openTicket = (ticket) => {


        setSelectedTicket(ticket);


        getMessages(ticket.id)

            .then(response => {

                setMessages(response.data);

            })

            .catch(error => {

                console.log(error);

            });


    };




    const handleSend = () => {


        sendMessage(

            selectedTicket.id,

            {
                message: message
            }

        )

            .then(() => {


                setMessage("");

                openTicket(selectedTicket);


            })

            .catch(error => {

                console.log(error);

            });


    };




    const changeStatus = (status) => {


        updateTicketStatus(

            selectedTicket.id,

            status

        )

            .then(() => {


                loadTickets();


                setSelectedTicket({

                    ...selectedTicket,

                    status: status

                });


            })


            .catch(error => {

                console.log(error);

            });


    };




    return (

        <div>


            <h1>

                Обращения пользователей

            </h1>



            {

                tickets.map(ticket => (


                    <div

                        className="support-ticket"

                        key={ticket.id}

                        onClick={() =>

                            openTicket(ticket)

                        }

                    >


                        {ticket.subject}

                        {" - "}

                        {ticket.status}


                    </div>


                ))

            }




            {

                selectedTicket &&


                <div>


                    <h2>

                        {selectedTicket.subject}

                    </h2>




                    <p>

                        Статус: {selectedTicket.status}

                    </p>



                    <button
                        onClick={() =>
                            changeStatus("CLOSED")
                        }
                    >

                        Закрыть обращение

                    </button>



                    <button
                        onClick={() =>
                            changeStatus("OPEN")
                        }
                    >

                        Открыть обращение

                    </button>




                    <h3>

                        Сообщения

                    </h3>




                    {

                        messages.map(item => (


                            <p key={item.id}>


                                {item.message}


                            </p>


                        ))

                    }




                    <input

                        placeholder="Ответ"

                        value={message}

                        onChange={(e) =>

                            setMessage(e.target.value)

                        }

                    />



                    <button onClick={handleSend}>

                        Отправить

                    </button>


                </div>

            }


        </div>

    );


};



export default AdminSupport;