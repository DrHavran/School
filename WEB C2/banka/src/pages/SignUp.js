function SignUp() {
  function SignIn(){
    fetch("http://localhost:8080/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ 
        username: document.getElementById("name").value,
        password: document.getElementById("password").value
      }),
    }).then(data => data.text())
      .then(data => {
          if(data === ""){
            window.location.href = "http://localhost:3000/login";
          }else{
            document.getElementById("error").innerHTML = data
          }
      })
  }
  return (
      <div>
        <h1>Sign up</h1>
        <div>
            <input id="name" type="text" placeholder="Account name"></input>
            <input id="password" type="password" placeholder="Password"></input>
            <button onClick={SignIn}>Sign up</button>
        </div>
        <p id="error"></p>
      </div>
    );
  }
  
  export default SignUp;