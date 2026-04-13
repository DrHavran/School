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
            data.addAccount(username);
        }
    }
    public String sendMoney(String username, String instance, double amount, String receiverId, String senderId){
        Account receiver = data.getAccount(receiverId);
        Account sender = data.getAccount(senderId);
        if(checkValidity(username, instance)){
            if(receiver == null || sender == null){
                return "Error: Receiver or Sender does not exist";
            }else if(!Objects.equals(receiverId.split("/")[0], Settings.bankId)){
                return "Not implemented yet - different bank";
            }else if(sender.getAmount() < amount){
                return "Not enough funds";
            }
            data.updateAccountAmount(receiverId, amount);
            data.updateAccountAmount(senderId, -amount);
            return "";
        }else{
            return "Not valid instance";
        }
    }

    public String getName(String instance){
        for(String key : activeUsers.keySet()){
            if(activeUsers.get(key).equals(instance)){
                return key;
            }
        }
        return "";
    }

    public void logOut(String instance, String username){
        if(checkValidity(username, instance)){
            activeUsers.remove(username);
            System.out.println("User " + username + " logged out");
        }
    }

    private boolean checkValidity(String username, String instance){
        return Objects.equals(instance, activeUsers.get(username));
    }
}
