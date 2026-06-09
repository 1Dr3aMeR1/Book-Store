import { useEffect, useState } from "react";

import {
    getOrders
} from "../../api/ordersApi";


function AdminOrders() {

    const [orders, setOrders] = useState([]);

    const loadOrders = () => {
        getOrders()
            .then(response => {
                setOrders(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    };


    useEffect(() => {
        loadOrders();
    }, []);


    return (
        <div className="admin-page">

            <h1>
                Управление заказами
            </h1>

            <div className="admin-list">

                {
                    orders.map(order => (

                        <div
                            className="admin-card"
                            key={order.id}
                        >

                            <div className="admin-card-title">
                                Заказ №{order.id}
                            </div>

                            <div className="admin-card-text">

                                Пользователь: {
                                order.user
                                    ?
                                    order.user.username
                                    :
                                    "Неизвестно"
                            }

                                <br />

                                Дата: {order.createdAt}

                                <br />

                                Статус: {order.status}

                            </div>


                            {
                                order.items &&

                                <div className="admin-card-text">

                                    <b>
                                        Товары:
                                    </b>

                                    {
                                        order.items.map(item => (

                                            <p key={item.id}>

                                                {item.book?.title}

                                                {" × "}

                                                {item.quantity}

                                            </p>

                                        ))
                                    }

                                </div>
                            }

                        </div>

                    ))
                }

            </div>

        </div>
    );

}


export default AdminOrders;