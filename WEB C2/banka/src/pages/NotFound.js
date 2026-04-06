import { Link } from "react-router-dom";

function NotFound() {
    return (
      <div>
        <h1>Page wasnt found, please go back gome</h1>
        <Link to="/">Home</Link>
      </div>
    );
  }
  
  export default NotFound;