import "./ManageUsers.css"
import UsersList from "../../components/UserList/UsersList.jsx";
import UserForm from "../../components/UserForm/UserForm.jsx";
const ManageUsers = () => {
    return (
        <div className="users-container text-light">
            <div className="left-column">
                <UserForm />
            </div>
            <div className="right-column">
              <UsersList />
            </div>
        </div>
    )
}
export default ManageUsers;