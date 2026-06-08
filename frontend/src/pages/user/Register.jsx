import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../../api/authApi";


function Register() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");

    const navigate = useNavigate();


    async function handleRegister() {
        try {
            await register({
                email,
                password,
                firstName,
                lastName
            });

            alert("Регистрация успешна");
            navigate("/login");

        } catch (error) {
            console.log(error);
            alert("Ошибка регистрации");
        }
    };


    return (

        <div className="auth-page">


            <h1>
                Регистрация
            </h1>

            <input
                placeholder="Имя"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
            />

            <input
                placeholder="Фамилия"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
            />

            <input

                placeholder="Email"

                onChange={
                    e => setEmail(e.target.value)
                }

            />


            <input

                placeholder="Пароль"

                type="password"

                onChange={
                    e => setPassword(e.target.value)
                }

            />


            <button
                onClick={handleRegister}
            >

                Зарегистрироваться

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
