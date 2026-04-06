import { Link } from "react-router-dom";

function Navigation() {
  return (
    <div>
        <Link to="/">Home</Link>
        <Link to="/login">Login</Link>
        <Link to="/signup">Sign Up</Link>
        <div>
            <Link to="/user">User</Link>
            <Link to="/account">Account</Link>
        </div>
    </div>
  );
}

export default Navigation;
