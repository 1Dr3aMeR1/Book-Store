import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getBookById } from "../../api/booksApi";
import { getBookInventory } from "../../api/inventoryApi";
import { getStoreById } from "../../api/storesApi";
import { useCart } from "../../context/CartContext";

function BookDetails() {
    const [book, setBook] = useState(null);
    const [inventory, setInventory] = useState([]);
    const [stores, setStores] = useState({});
    const { id } = useParams();
    const { addToCart } = useCart();

    useEffect(() => {
        getBookById(id)
            .then(response => {
                setBook(
                    response.data
                );
            })

            .catch(error => {
                console.log(error);
            });

        getBookInventory(id)
            .then(async response => {
                setInventory(
                    response.data
                );
                const storesMap = {};
                for (const item of response.data) {
                    try {
                        const storeResponse =
                            await getStoreById(
                                item.storeId
                            );
                        storesMap[item.storeId] =
                            storeResponse.data;
                    } catch (error) {
                        console.log(error);
                    }
                }

                setStores(
                    storesMap
                );
            })

            .catch(error => {
                console.log(error);
            });
    }, [id]);

    if (!book) {
        return (
            <h2>
                Загрузка...
            </h2>
        );
    }

    return (
        <div>
            {
                book.previewUrl &&
                <img
                    className="book-detail-image"
                    src={book.previewUrl}
                    alt={book.title}
                />
            }

            <h1>
                {book.title}

            </h1>

            <div className="book-info">
                <div className="info-block">
                    <span>
                        Автор
                    </span>
                    <p>
                        {book.author}
                    </p>
                </div>

                <div className="info-block">
                    <span>
                        Описание
                    </span>
                    <p>
                        {book.description}
                    </p>
                </div>

                <div className="info-row">
                    <div className="info-block">
                        <span>
                            ISBN
                        </span>

                        <p>
                            {book.isbn}
                        </p>
                    </div>

                    <div className="info-block">
                        <span>
                            Год выпуска
                        </span>

                        <p>
                            {book.publicationYear}
                        </p>
                    </div>
                </div>

                <div className="info-block">
                    <span>
                        Наличие в магазинах
                    </span>

                    {
                        inventory.length === 0
                            ? (
                                <p>
                                    Нет в наличии
                                </p>
                            )

                            : (
                                inventory.map(item => (
                                    <div
                                        className="store-card"
                                        key={item.id}
                                    >
                                        <p>
                                            {
                                                stores[item.storeId]
                                                    ? `${stores[item.storeId].name} / ${stores[item.storeId].address}`
                                                    : "Загрузка магазина..."
                                            }
                                            {" : "}
                                            {item.quantity}
                                            {" шт."}
                                        </p>
                                    </div>
                                ))
                            )
                    }
                </div>

                <div className="price-block">
                    {book.price} ₽
                </div>

                <button
                    className="cart-button"
                    onClick={() =>
                        addToCart(book)
                    }
                >
                    Добавить в корзину
                </button>
            </div>
        </div>
    );
}

export default BookDetails;
