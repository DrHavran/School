import { Link } from "react-router-dom";

function Login() {
    function logIn(){
      let randomString = Math.random().toString(36).substring(2, 12);
      let user = document.getElementById("name").value
      fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ 
          username: user,
          password: document.getElementById("password").value,
          instance: randomString
        }),
      }).then(data => data.text())
        .then(data => {
          if(data === ""){
            window.location.href = `http://localhost:3000/user/${user}?instance=${randomString}`;
          }else{
            document.getElementById("error").innerHTML = data
          }
      })
    }

    return (
      <div>
        <h1>Log in</h1>
        <div>
            <input id="name" type="text" placeholder="Account name"></input>
            <input id="password" type="password" placeholder="Password"></input>
            <button onClick={logIn}>Log in</button>
        </div>
        <p id="error"></p>
        <Link to="/signup">Sign Up</Link>
      </div>
    );
  }
  
  export default Login;