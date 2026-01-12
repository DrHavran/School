import { useLocation } from "react-router-dom";

function Navigation() {
  const { pathname } = useLocation();

  return (
    <ul className="nav nav-tabs mt-3">
      <li className="nav-item px-2 ps-5">
        <a className={`nav-link${pathname === "/" ? " active" : ""}`} href="/">Home</a>
      </li>
      <li className="nav-item px-2">
        <a className={`nav-link${pathname === "/expenses" ? " active" : ""}`} href="/expenses">Expenses</a>
      </li>
    </ul>
  );
}

export default Navigation;
