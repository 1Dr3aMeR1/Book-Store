import { useEffect, useState } from "react";
import { useCart } from "../../context/CartContext";
import { getBookInventory } from "../../api/inventoryApi";
import { getStoreById } from "../../api/storesApi";
import { createOrder } from "../../api/ordersApi";

const Cart = () => {
    const {
        cart,
        removeFromCart,
        changeCount,
        clearCart
    } = useCart();

    const [stores, setStores] = useState({});
    const [selectedStores, setSelectedStores] = useState({});

    useEffect(() => {
        const loadStores = async () => {
            const result = {};
            for (const book of cart) {
                const response = await getBookInventory(book.id);
                result[book.id] = [];

                for (const item of response.data) {
                    if (item.quantity > 0) {
                        const store =
                            await getStoreById(item.storeId);
                        result[book.id].push({
                            ...store.data,
                            quantity: item.quantity
                        });
                    }
                }
            }
            setStores(result);
        };
        loadStores();
    }, [cart]);

    const total = cart.reduce(
        (sum, book) => sum + book.price * book.count, 0
    );

    const handlePayment = async () => {
        for (const book of cart) {
            if (!selectedStores[book.id]) {
                alert(
                    "Выберите магазин для всех книг"
                );
                return;
            }

            const store = stores[book.id].find(item =>
                    Number(item.id) === Number(selectedStores[book.id])
            );

            if (book.count > store.quantity) {
                alert(
                    `В магазине "${store.name}" доступно только ${store.quantity} шт.`
                );
                return;
            }
        }

        const order = {
            items: cart.map(book => ({
                bookId: book.id,
                quantity: book.count
            }))
        };

        try {
            await createOrder(order);
            alert(
                "Заказ успешно оформлен"
            );
            clearCart();
        }

        catch (error) {
            console.log(error);
            alert(
                "Ошибка оформления заказа"
            );
        }
    };

    if (cart.length === 0) {
        return (
            <div>
                <h1>
                    Корзина
                </h1>
                <p>
                    Корзина пуста
                </p>
            </div>
        );
    }

    return (
        <div>
            <h1>
                Корзина
            </h1>

            {
                cart.map(book => (
                    <div
                        className="cart-card"
                        key={book.id}
                    >
                        {
                            book.previewUrl &&
                            <img
                                className="cart-image"
                                src={book.previewUrl}
                                alt={book.title}
                            />
                        }

                        <div>
                            <h2>
                                {book.title}
                            </h2>

                            <p>
                                Автор: {book.author}
                            </p>

                            <p>
                                Цена: {book.price} ₽
                            </p>

                            <div className="cart-count">
                                <button
                                    onClick={() =>
                                        changeCount(
                                            book.id,
                                            book.count - 1
                                        )
                                    }
                                >
                                    -
                                </button>

                                <span>
                                    {book.count}
                                </span>

                                <button
                                    onClick={() =>
                                        changeCount(
                                            book.id,
                                            book.count + 1
                                        )
                                    }
                                >
                                    +
                                </button>
                            </div>

                            <div className="cart-actions">
                                <select
                                    value={
                                        selectedStores[book.id] || ""
                                    }
                                    onChange={(e) =>
                                        setSelectedStores({
                                            ...selectedStores,
                                            [book.id]: e.target.value
                                        })
                                    }
                                >
                                    <option value="">
                                        Выберите магазин
                                    </option>

                                    {
                                        stores[book.id]?.map(store => (
                                            <option
                                                key={store.id}
                                                value={store.id}
                                            >
                                                {store.name}
                                                {" ("}
                                                {store.quantity}
                                                {" шт.)"}
                                            </option>
                                        ))
                                    }
                                </select>

                                <button
                                    className="cart-delete"
                                    onClick={() =>
                                        removeFromCart(
                                            book.id
                                        )
                                    }
                                >
                                    Удалить
                                </button>
                            </div>
                        </div>
                    </div>
                ))
            }

            <div className="cart-footer">
                <p className="cart-total">
                    Итого: {total} ₽
                </p>

                <button
                    className="order-button"
                    onClick={handlePayment}
                >
                    Оплатить
                </button>
            </div>
        </div>
    );
};

export default Cart;