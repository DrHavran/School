import { Link, useSearchParams } from "react-router-dom";
import { useState, useEffect } from "react";

function Navigation() {
  let [searchParams] = useSearchParams();
  let instance = searchParams.get("instance");
  const [user, setUser] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/getName", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ instance: instance }),
    })
      .then((res) => res.text())
      .then((text) => {
        if (text !== "") {
          document.getElementById("logged").style.display = "block";
          document.getElementById("not").style.display = "none";
          document.getElementById("userLink").innerHTML = text;
          setUser(text);
        }
      });
  }, [instance]);

  function logOut(){
    fetch("http://localhost:8080/logout", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ 
            username: user,
            instance: instance
        }),
    }).then(() => {
        window.location.href = "http://localhost:3000/";
    });
}

  return (
    <nav>
      <Link to={`/?instance=${instance}`}>Home</Link>
      <div id="not">
        <Link to="/login">Login</Link>
        <Link to="/signup">Sign Up</Link>
      </div>
      <div id="logged" style={{ display: "none" }}>
        <Link id="userLink" to={`/user/${user}?instance=${instance}`}></Link>
        <button onClick={logOut} className="home-button-red">Log out</button>
      </div>
    </nav>
  );
}

export default Navigation;