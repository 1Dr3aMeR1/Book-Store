import { Link } from "react-router-dom";
function AdminPanel() {
    return (
        <div className="admin-page">
            <h1>
                Панель администратора
            </h1>

            <div className="admin-menu">
                <Link to="/admin/books">
                    Управление книгами
                </Link>

                <Link to="/admin/categories">
                    Управление категориями
                </Link>

                <Link to="/admin/stores">
                    Управление магазинами
                </Link>

                <Link to="/admin/orders">
                    Управление заказами
                </Link>
            </div>
        </div>
    );
}

export default AdminPanel;