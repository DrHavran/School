package org.server.server;

import org.server.server.Helpers.Data;
import org.server.server.Helpers.Hashing;
import org.server.server.Parts.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Logic {

    private final Data data;
    private final HashMap<String, String> activeUsers; //Add expiration date?
    public Logic(){
        this.data = new Data();
        this.activeUsers = new HashMap<>();
    }

    public String checkLogin(String username, String password, String instance){
        String hashedUser = Hashing.hash(username.toLowerCase());
        String hashedPassword = Hashing.hash(password);
        for(String name : data.getUsers().keySet()){
            if(name.equals(hashedUser)){
                if(data.getUsers().get(name).equals(hashedPassword)){
                    System.out.println("Log in credentials match");
                    activeUsers.put(username, instance);
                    System.out.println("Adding active user: " + username + " " + instance);
                    return "";
                }
                System.out.println("Incorrect password");
                return "Incorrect password";
            }
        }
        System.out.println("Username does not exist");
        return "Username does not exist";
    }
    public String checkSignUp(String username, String password){
        String hashedUser = Hashing.hash(username.toLowerCase());
        String hashedPassword = Hashing.hash(password);
        if(!data.getUsers().containsKey(hashedUser)){
            data.writeUser(hashedUser, hashedPassword);
            System.out.println("User created");
            return "";
        }
        System.out.println("Username already exists");
        return "Username already exists";
    }
    public ArrayList<Account> getAccounts(String username, String instance){
        if(checkValidity(username, instance)){
            return data.getAccounts(username);
        }
        return null;
    }
    public void createAccount(String username, String instance){
        if(checkValidity(username, instance)){
            data.writeAccount(username);
        }
    }

    private boolean checkValidity(String username, String instance){
        return Objects.equals(instance, activeUsers.get(username));
    }
}
