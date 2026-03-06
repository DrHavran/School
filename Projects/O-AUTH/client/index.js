fetch("http://localhost:8000/")
  .then(response => response.text())
  .then(data => console.log(data));


function askForToken(){
    fetch("http://localhost:8000/?redirect", {
        method: "POST",
    }).then(response => response.text()).then(data => console.log(data))
}