// src/context/AppContext.jsx
import { createContext, useState, useEffect } from "react";
import { fetchCategories } from "../service/Categoryservice.js";

// Create Context
export const AppContext = createContext(null);

// Provider Component
export const AppContextProvider = ({ children }) => {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        async function loadData() {
            try {
                const response = await fetchCategories();
                setCategories(response.data);
            } catch (error) {
                console.error("Failed to fetch categories:", error);
            }
        }
        loadData();
    }, []);

    const contextValue = {
        categories,
        setCategories,
    };

    return (
        <AppContext.Provider value={contextValue}>
            {children}
        </AppContext.Provider>
    );
};
