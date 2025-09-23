window.onload = function () {
    const svátekText = document.getElementById("svátek")
    fetch("https://svatkyapi.cz/api/day")         
      .then(response => response.json())          
      .then(data => {                
        svátekText.innerHTML = "Dnes má svátek " + data.name + ", nezapomeň koupit kytku!"            
    })
};