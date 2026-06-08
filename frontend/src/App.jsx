import { Routes, Route } from "react-router-dom";

import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./pages/user/Home";
import Catalog from "./pages/user/Catalog";
import BookDetails from "./pages/user/BookDetails";
import Login from "./pages/user/Login";
import Register from "./pages/user/Register";
import Cart from "./pages/user/Cart";
import Orders from "./pages/user/Orders";
import Payment from "./pages/user/Payment";

import AdminPanel from "./pages/admin/AdminPanel";
import AdminBooks from "./pages/admin/AdminBooks";
import AdminCategories from "./pages/admin/AdminCategories";
import AdminStores from "./pages/admin/AdminStores";
import AdminOrders from "./pages/admin/AdminOrders";


function App() {
    return (
        <>
            <Header />
            <main>
                <Routes>
                    <Route
                        path="/"
                        element={<Home />}
                    />

                    <Route
                        path="/catalog"
                        element={<Catalog />}
                    />

                    <Route
                        path="/books/:id"
                        element={<BookDetails />}
                    />

                    <Route
                        path="/login"
                        element={<Login />}
                    />

                    <Route
                        path="/register"
                        element={<Register />}
                    />

                    <Route
                        path="/cart"
                        element={<Cart />}
                    />

                    <Route
                        path="/orders"
                        element={<Orders />}
                    />

                    <Route
                        path="/payment"
                        element={<Payment />}
                    />

                    <Route
                        path="/admin"
                        element={<AdminPanel />}
                    />
                    <Route
                        path="/admin/books"
                        element={<AdminBooks />}
                    />

                    <Route
                        path="/admin/categories"
                        element={<AdminCategories />}
                    />

                    <Route
                        path="/admin/stores"
                        element={<AdminStores />}
                    />

                    <Route
                        path="/admin/orders"
                        element={<AdminOrders />}
                    />
                </Routes>
            </main>
            <Footer />
        </>
    );
}

export default App;