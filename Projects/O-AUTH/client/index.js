let correct = "https://media.istockphoto.com/id/1133442802/vector/green-checkmark-vector-illustration.jpg?s=612x612&w=0&k=20&c=NqyVOdwANKlbJNqbXjTvEp2wIZWUKbfUbRxm9ROPk6M="
let incorrect = "https://clipart-library.com/new_gallery/51-515337_x-mark-png-png-download-transparent-background-red.png"

window.onload = function() {
  document.getElementById("check").src = incorrect
}


function askForToken(){
    fetch("http://localhost:8000/?redirect", {
        method: "POST",
    }).then(response => response.text()).then(data => console.log(data))
}


fetch("http://localhost:8000/")
  .then(response => response.text())
  .then(data => console.log(data));