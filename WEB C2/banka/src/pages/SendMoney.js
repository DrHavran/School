import { useParams, useSearchParams } from "react-router-dom";
import { useEffect } from "react";

function SendMoney() {
    const { id } = useParams();
    let [searchParams] = useSearchParams();
    let instance = searchParams.get("instance");

    useEffect(() => {
        getAccounts()
    }, [id, instance])

    function getAccounts(){
        fetch("http://localhost:8080/getAccounts", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ 
            username: id,
            instance: instance
            }),
        }).then(res => res.text())   
            .then(text => {
            if (!text || text === "null") {
                window.location.href = "http://localhost:3000/login";
                return;
            }
            const data = JSON.parse(text);
            document.getElementById("mainDiv").style.visibility = "visible";
            data.forEach(account => {
                const option = document.createElement("option");
                option.value = account.id
                option.text = account.id
                document.getElementById("select").appendChild(option);
            })})
    }  

    function sendMoney(){
        fetch("http://localhost:8080/sendMoney", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ 
                username: id,
                instance: instance,
                amount: document.getElementById("amount").value,
                senderAccId: document.getElementById("select").value,
                receiverAccId: document.getElementById("receiver").value
            }),
        }).then(res => res.text())   
            .then(text => {
            if (text === "") {
                window.location.href = `http://localhost:3000/user/${id}?instance=${instance}`;
                return;
            }else{
                document.getElementById("error").innerHTML = text
            }
        })
    }

    return (
        <div id="mainDiv" style={{ visibility: "hidden" }}>
            <select id="select" defaultValue={""}>
                <option value="" disabled>Select an account</option>
            </select>
            <input type="text" placeholder="Receiver account" id="receiver"></input>
            <input type="number" placeholder="Amount" min={1} id="amount"></input>
            <button onClick={sendMoney}>Send</button>
            <p id="error"></p>
        </div>
    );
}

export default SendMoney;