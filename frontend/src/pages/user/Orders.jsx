import { useEffect, useState } from "react";
import { getMyOrders } from "../../api/ordersApi";

const Orders = () => {
    const [orders, setOrders] = useState([]);
    useEffect(() => {
        getMyOrders()
            .then(response => {
                console.log(
                    "Заказы:",
                    response.data
                );

                setOrders(
                    response.data
                );
            })

            .catch(error => {
                console.log(error);
            });
    }, []);

    if (orders.length === 0) {
        return (
            <div>
                <h1>
                    Мои заказы
                </h1>

                <p>
                    У вас пока нет заказов
                </p>
            </div>
        );
    }

    return (
        <div>
            <h1>
                Мои заказы
            </h1>

            {
                orders.map(order => (
                    <div
                        className="order-card"
                        key={order.id}
                    >
                        <h2>
                            Заказ №{order.id}
                        </h2>

                        <p>
                            Дата: {
                            new Date(order.createdAt).toLocaleDateString()
                        }
                        </p>

                        <p>
                            Статус: {order.status}
                        </p>

                        <p>
                            Сумма: {order.totalPrice} ₽
                        </p>
                    </div>
                ))
            }
        </div>
    );
};

export default Orders;