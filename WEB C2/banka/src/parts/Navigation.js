import { Link } from "react-router-dom";

function Navigation() {
  return (
    <nav>
        <Link to="/">Home</Link>
        <div>
          <Link to="/login">Login</Link>
          <Link to="/signup">Sign Up</Link>
        </div>
    </nav>
  );
}

export default Navigation;
