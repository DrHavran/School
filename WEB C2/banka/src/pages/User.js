import Account from "../parts/Account"

import { useParams, useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";

function User() {
    const [accounts, setAccounts] = useState([]);

    const { id } = useParams();
    let [searchParams] = useSearchParams();
    let instance = searchParams.get("instance");

    useEffect(() => {
      reloadAcc()
    }, [id, instance])

    function reloadAcc(){
      fetch("http://localhost:8080/getAccounts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ 
          username: id,
          instance: instance
        }),
      }).then(res => res.text())   
        .then(text => {
          if (!text) {
            window.location.href = "http://localhost:3000/login";
            return;
          }
          const data = JSON.parse(text);
          document.getElementById("main").style.visibility = "visible";
          setAccounts(data);
        })
    }

    function createAccount(){
      fetch("http://localhost:8080/createAccount", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ 
          username: id,
          instance: instance
        }),
      }).then(() => reloadAcc())
    }

    return (
      <div id="main" style={{ visibility: "hidden" }}>
          {accounts.map((acc) => (<Account key={acc.id} account={acc}/>))}
          <button>Send money</button>
          <button onClick={createAccount}>Create account</button>
      </div>
    );
  }
  
export default User;