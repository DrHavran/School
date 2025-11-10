let users = [];
    
function addUser() {
    let firstname = document.getElementById('firstName').value;
    let lastname = document.getElementById('lastName').value;
    let email = document.getElementById('email').value;
    let age = document.getElementById('age').value;
        
    users.push(new User(firstname, lastname, email, age));
    console.log(users.at(users.length-1));
}

function readUsers(){
    for(let user of users){
        console.log(user.toString());
    }
}

function deleteUser(){
    let emailToDelete = document.getElementById('deleteEmail').value;

    for(let user of users){
        if(user.email == emailToDelete){
            let found = users.indexOf(user)
            users.splice(found, 1)
            console.log("User removed")
            return;
        }
    }
    console.log("This email is not registered")
}

class User {
    constructor(firstname, lastname, email, age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }
    
    toString() {
        return `Name: ${this.firstname} ${this.lastname}, Age: ${this.age}, E-mail: ${this.email}`;
    }
}