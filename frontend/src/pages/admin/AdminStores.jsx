import { useEffect, useState } from "react";

import {
    getStores,
    createStore,
    updateStore,
    deleteStore
} from "../../api/storesApi";

function AdminStores() {
    const [stores, setStores] = useState([]);

    const [form, setForm] = useState({
        name: "",
        address: ""
    });

    const [editId, setEditId] = useState(null);

    const loadStores = () => {
        getStores()
            .then(response => {
                setStores(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    };

    useEffect(() => {
        loadStores();
    }, []);

    const saveStore = () => {
        if (editId) {
            updateStore(editId, form)
                .then(() => {
                    setEditId(null);
                    clearForm();
                    loadStores();
                });
        }
        else {
            createStore(form)
                .then(() => {
                    clearForm();
                    loadStores();
                });
        }
    };

    const editStore = (store) => {
        setEditId(store.id);

        setForm({
            name: store.name,
            address: store.address
        });
    };

    const removeStore = (id) => {
        deleteStore(id)
            .then(() => {
                loadStores();
            });
    };

    const clearForm = () => {
        setForm({
            name: "",
            address: ""
        });
    };

    return (
        <div className="admin-page">

            <h1>
                Управление магазинами
            </h1>

            <div className="admin-form">

                <input
                    placeholder="Название магазина"
                    value={form.name}
                    onChange={(e) =>
                        setForm({
                            ...form,
                            name: e.target.value
                        })
                    }
                />

                <input
                    placeholder="Адрес"
                    value={form.address}
                    onChange={(e) =>
                        setForm({
                            ...form,
                            address: e.target.value
                        })
                    }
                />

                <button onClick={saveStore}>
                    {
                        editId
                            ?
                            "Сохранить"
                            :
                            "Добавить"
                    }
                </button>

            </div>

            <div className="admin-list">

                {
                    stores.map(store => (

                        <div
                            className="admin-card"
                            key={store.id}
                        >

                            <div className="admin-card-title">
                                {store.name}
                            </div>

                            <div className="admin-card-text">
                                Адрес: {store.address}
                            </div>

                            <div className="admin-actions">

                                <button
                                    onClick={() =>
                                        editStore(store)
                                    }
                                >
                                    Изменить
                                </button>

                                <button
                                    onClick={() =>
                                        removeStore(store.id)
                                    }
                                >
                                    Удалить
                                </button>

                            </div>

                        </div>

                    ))
                }

            </div>

        </div>
    );
}

export default AdminStores;