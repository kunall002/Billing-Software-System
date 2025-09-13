import Menubar from "./components/Menubar/Menubar.jsx";
import {Routes, Route, useLocation} from "react-router-dom";
import Dashboard from "./Pages/Dashboard/Dashboard.jsx";
import ManageCategory from "./Pages/ManageCategory/ManageCategory.jsx";
import Explore from "./Pages/Explore/Explore.jsx";
import ManageUsers from "./Pages/ManageUsers/ManageUsers.jsx";
import ManageItems from "./Pages/ManageItems/ManageItems.jsx";
import {Toaster} from "react-hot-toast";
import Login from "./Pages/Login/Login.jsx";

const App = () => {
  const location = useLocation();
  return (
    <div>
        {location.pathname !== "/login" && <Menubar />}
        <Toaster />
        <Routes>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/category" element={<ManageCategory />} />
            <Route path="/users" element={<ManageUsers />} />
            <Route path="/item" element={<ManageItems />} />
            <Route path="explore" element={<Explore />} />
            <Route path="/login" element={<Login />} />
            <Route path="/" element={<Dashboard />} />


        </Routes>
    </div>
  )
}
export default App;