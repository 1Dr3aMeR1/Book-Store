import { createContext, useContext, useEffect, useState } from "react";

const CartContext = createContext();
export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState(() => {
        const savedCart = localStorage.getItem("cart");
        return savedCart
            ? JSON.parse(savedCart)
            : [];
    });

    useEffect(() => {
        localStorage.setItem(
            "cart",
            JSON.stringify(cart)
        );
    }, [cart]);

    const addToCart = (book) => {
        const exists = cart.find(
            item => item.id === book.id
        );

        if (exists) {
            setCart(
                cart.map(item => item.id === book.id ? {
                            ...item,
                            count: item.count + 1
                        }
                        : item
                )
            );
        }

        else {
            setCart([
                ...cart,
                {
                    ...book,
                    count: 1
                }
            ]);
        }
    };

    const removeFromCart = (id) => {
        setCart(
            cart.filter(
                item => item.id !== id
            )
        );
    };

    const clearCart = () => {
        setCart([]);
    };

    const changeCount = (id, count) => {
        if (count < 1) {
            return;
        }

        setCart(
            cart.map(item =>
                item.id === id ? {
                        ...item,
                        count: count
                    }
                    : item
            )
        );
    };

    return (
        <CartContext.Provider
            value={{
                cart,
                addToCart,
                removeFromCart,
                changeCount,
                clearCart
            }}
        >
            {children}
        </CartContext.Provider>
    );
};

export const useCart = () => {
    return useContext(CartContext);
};