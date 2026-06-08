import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function AdminRoute({ children }) {
    const { token } = useAuth();
    if (!token) {
        return <Navigate to="/" />;
    }

    const user = JSON.parse(
        atob(
            token.split(".")[1]
        )
    );

    const isAdmin = user.sub === "user";
    if (!isAdmin) {
        return <Navigate to="/" />;
    }
    return children;
}

export default AdminRoute;