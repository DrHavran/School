class User {
    constructor(firstname, lastname, email, age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }
    
    ToString() {
        return `Name: ${this.firstname} ${this.lastname}, Age: ${this.age}, E-mail: ${this.email}`;
    }
}