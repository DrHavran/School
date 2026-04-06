import Account from "../parts/Account"

import { useParams, useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";

function User() {
    const [accounts, setAccounts] = useState([]);

    const { id } = useParams();
    let [searchParams] = useSearchParams();
    let instance = searchParams.get("instance");

    useEffect(() => {
      fetch("http://localhost:8080/getAccounts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ 
          username: id,
          instance: instance
        }),
      }).then(data => data.json())
        .then(data => {
          if(data == undefined){
            window.location.href = "http://localhost:3000/login";
          }
          document.getElementById("main").style.visibility = "visible"
          setAccounts(data)
      })
    }, [id, instance])

    return (
      <div id="main" style={{ visibility: "hidden" }}>
          {accounts.map((acc) => (<Account account={acc}/>))}
          <button>Send money</button>
          <button>Create account</button>
      </div>
    );
  }
  
export default User;