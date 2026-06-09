import { useEffect, useState } from "react";
import {
    getCategories,
    createCategory,
    updateCategory,
    deleteCategory
} from "../../api/categoriesApi";

const AdminCategories = () => {
    const [categories, setCategories] = useState([]);
    const [name, setName] = useState("");
    const [editId, setEditId] = useState(null);
    const loadCategories = () => {
        getCategories()
            .then(response => {
                setCategories(response.data);
            });
    };

    useEffect(() => {
        loadCategories();
    }, []);

    const saveCategory = () => {
        if (editId) {
            updateCategory(
                editId,
                {
                    name: name
                }
            )
                .then(() => {
                    setEditId(null);
                    setName("");
                    loadCategories();
                });
        }
        else {
            createCategory({
                name: name
            })
                .then(() => {
                    setName("");
                    loadCategories();
                });
        }
    };

    const editCategory = (category) => {
        setEditId(category.id);
        setName(category.name);
    };

    const removeCategory = (id) => {
        deleteCategory(id)
            .then(() => {
                loadCategories();
            });
    };

    return (
        <div className="admin-page">
            <h1>
                Управление категориями
            </h1>

            <div className="admin-form">
                <input
                    placeholder="Название категории"
                    value={name}
                    onChange={(e) =>
                        setName(e.target.value)
                    }
                />

                <button onClick={saveCategory}>
                    {
                        editId ?
                            "Сохранить"
                            :
                            "Добавить"
                    }
                </button>
            </div>

            {

                categories.map(category => (
                    <div
                        className="admin-card"
                        key={category.id}
                    >
                        <div className="admin-card-title">
                            {category.name}
                        </div>
                        <div className="admin-actions">
                            <button
                                onClick={() => editCategory(category)
                                }
                            >
                                Изменить
                            </button>

                            <button
                                onClick={() =>
                                    removeCategory(category.id)
                                }
                            >
                                Удалить
                            </button>
                        </div>
                    </div>
                ))
            }
        </div>
    );
};

export default AdminCategories;