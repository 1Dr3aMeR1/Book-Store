import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function Header() {
    const { token, logout } = useAuth();
    let isAdmin = false;
    if (token) {
        const user = JSON.parse(
            atob(
                token.split(".")[1]
            )
        );
        isAdmin = user.sub === "user";
    }

    return (
        <header className="header">
            <div className="header-left">
                <Link to="/">
                    Главная
                </Link>

                <Link to="/catalog">
                    Каталог
                </Link>
            </div>

            <div className="header-right">
                <Link to="/cart">
                    Корзина
                </Link>

                <Link to="/orders">
                    Заказы
                </Link>

                {
                    token && isAdmin &&
                    <Link to="/admin">
                        Админ
                    </Link>
                }

                {
                    token ?
                        <button onClick={logout}>
                            Выйти
                        </button>
                        :
                        <Link to="/login">
                            Вход
                        </Link>
                }
            </div>
        </header>
    );
}

export default Header;