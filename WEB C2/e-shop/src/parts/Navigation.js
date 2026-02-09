import { useLocation, Link } from "react-router-dom";

function Navigation() {
  const { pathname } = useLocation();

  return (
    <ul className="nav nav-tabs mt-3">
      <li className="nav-item px-2 ps-5">
        <Link className={`nav-link${pathname === "/" ? " active" : ""}`} to="/">Home</Link>
      </li>
      <li className="nav-item px-2">
        <Link className={`nav-link${pathname === "/catalog" ? " active" : ""}`} to="/catalog">Catalog</Link>
      </li>
      <li className="nav-item px-2">
        <Link className={`nav-link${pathname === "/cart" ? " active" : ""}`} to="/cart">🛒Cart</Link>
      </li>
    </ul>
  );
}

export default Navigation;
