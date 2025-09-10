import { AppContext } from "../../Context/AppContext.jsx";
import { useContext, useState } from "react";
import './CategoriesList.css';
import { deleteCategory } from "../../service/Categoryservice.js";
import toast from "react-hot-toast";

const CategoryList = () => {
    const { categories, setCategories } = useContext(AppContext);
    const [searchTerm, setSearchTerm] = useState('');

    // 🔍 Search filter
    const filteredCategories = categories.filter(category =>
        category.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    // 🗑️ Delete category by ID
    const deleteByCategoryId = async (categoryId) => {
        try {
            const response = await deleteCategory(categoryId);
            if (response.status === 204 || response.status === 200) {
                const updatedCategories = categories.filter(c => c.categoryId !== categoryId);
                setCategories(updatedCategories);
                toast.success("Category deleted successfully.");
            } else {
                toast.error("Unable to delete the category.");
            }
        } catch (error) {
            console.error(error);
            toast.error("Unable to delete the category.");
        }
    };

    return (
        <div className="categories-list-container" style={{ height: '100vh', overflowY: 'auto', overflowX: 'auto' }}>

            {/* 🔍 Search bar */}
            <div className="row pe-2">
                <div className="input-group mb-3">
                    <input
                        type="text"
                        name="keyword"
                        id="keyword"
                        placeholder="Search by keyword"
                        className="form-control"
                        onChange={(e) => setSearchTerm(e.target.value)}
                        value={searchTerm}
                    />
                    <span className="input-group-text bg-warning">
                        <i className="bi bi-search"></i>
                    </span>
                </div>
            </div>

            {/* 📋 Categories list */}
            <div className="row g-3 pe-2">
                {filteredCategories.map((category) => (
                    <div key={category.categoryId} className="col-12">
                        <div className="card p-3" style={{ backgroundColor: category.bgColor }}>
                            <div className="d-flex align-items-center">
                                <div style={{ marginRight: '15px' }}>
                                    <img src={category.imgUrl} alt={category.name} className="category-image" />
                                </div>
                                <div className="flex-grow-1">
                                    <h5 className="mb-1 text-white">{category.name}</h5>
                                    <p className="mb-0 text-white">{category.items} Items</p>
                                </div>
                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => deleteByCategoryId(category.categoryId)}
                                >
                                    <i className="bi bi-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CategoryList;
