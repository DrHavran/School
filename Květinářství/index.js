window.onload = function () {
    const svátekText = document.getElementById("svátek")
    fetch("https://svatkyapi.cz/api/day")         
      .then(response => response.json())          
      .then(data => {                
        svátekText.innerHTML = "Dnes má svátek " + data.name + ", nezapomeň koupit kytku!"            
    })
};
function toggle(what){
  const menu = document.getElementById(what);
  const currentDisplay = window.getComputedStyle(menu).display;

  menu.style.display = (currentDisplay === "none") ? "block" : "none";
}
