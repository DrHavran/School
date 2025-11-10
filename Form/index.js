import User from "./User"

let users = [];
    
function addUser() {
    let firstname = document.getElementById('firstname').value;
    let lastname = document.getElementById('lastname').value;
    let email = document.getElementById('email').value;
    let age = document.getElementById('age').value;
        
    users.push(new User(firstname, lastname, email, age));
    console.log("You are the " + users.length + " user")
    console.log(users.at(users.length-1));
}

function readUsers(){
    for(let user of users){
        console.log(user.toString());
    }
}

function deleteUser(email){
    for(let user of users){
        if(user.email == email){
            let found = users.indexOf(user)
            users.splice(found, 1)
            console.log("User removed")
            return;
        }
    }
    console.log("This email is not registered")
}