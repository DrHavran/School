let appID = "nabytek.cz"
let fakeCookiesState = "seruNaVšechno"
let scope = "name"
let redirect_uri = "http://127.0.0.1:5500/Client/redirect.html"

let params = new URLSearchParams(window.location.search);

window.onload = function(){
    if(fakeCookiesState === params.get("state")){
        fetch("http://localhost:8000/getToken", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ 
                code: params.get("code"),
                id: appID,
                redirect_uri: redirect_uri,
                state: params.get("state"),
                scope: scope
            })
        }).then(response => response.text())
        .then(data => {
            
        });
    }else{
        
    }
}