let appID = "nabytek.cz"
let fakeCookiesState = "seruNaVšechno"
let scope = "name"
let redirect_uri = "http://127.0.0.1:5500/Client/redirect.html"

function goTo(){
  window.location.replace(`http://127.0.0.1:5500/OAUTH/oauth.html?response_type=code&client_id=${appID}&redirect_uri=${redirect_uri}&scope=${scope}&state=${fakeCookiesState}`);
}