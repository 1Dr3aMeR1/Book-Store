import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../../api/authApi";
import { useAuth } from "../../context/AuthContext";
function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const auth = useAuth();
    const navigate = useNavigate();
    const handleLogin = async () => {
        try {
            const response = await login({
                email,
                password
            });

            console.log(
                response.data
            );

            auth.login(
                response.data.token
            );

            navigate("/");
        } catch (error) {
            alert(
                "Неверный email или пароль"
            );
        }
    };

    return (
        <div className="auth-page">
            <h1>
                Авторизация
            </h1>

            <input
                placeholder="Email"
                value={email}
                onChange={
                    (e) =>
                        setEmail(e.target.value)
                }
            />

            <input
                placeholder="Пароль"
                type="password"
                value={password}
                onChange={
                    (e) =>
                        setPassword(e.target.value)
                }
            />

            <button
                onClick={handleLogin}
            >
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


