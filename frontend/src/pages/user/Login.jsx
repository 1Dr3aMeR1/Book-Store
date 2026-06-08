import { Link } from "react-router-dom";

function Login() {
    return (
        <div className="auth-page">

            <h1>
                Авторизация
            </h1>

            <input
                placeholder="Email"
            />

            <input
                placeholder="Пароль"
                type="password"
            />
            <button>
                Войти
            </button>

            <p>
                Нет аккаунта?
                {" "}
                <Link to="/register">
                    Зарегистрироваться
                </Link>
            </p>
        </div>
    );
}

export default Login;