import { Link } from "react-router-dom";

function Header() {
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

                <Link to="/login">
                    Вход
                </Link>
            </div>
        </header>
    );
}

export default Header;