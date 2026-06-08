import { Link } from "react-router-dom";
function Register() {
    return (
        <div className="auth-page">
            <h1>
                Регистрация
            </h1>

            <input
                placeholder="Имя"
            />

            <input
                placeholder="Email"
            />

            <input
                placeholder="Пароль"
                type="password"
            />

            <button>
                Создать аккаунт
            </button>

            <p>
                Уже есть аккаунт?
                {" "}
                <Link to="/login">
                    Войти
                </Link>
            </p>
        </div>
    );
}


export default Register;