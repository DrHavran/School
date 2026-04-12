package org.server.server.Helpers;

import org.server.server.Parts.Account;
import org.server.server.Settings;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Data {

    private final HashMap<String, String> users;
    private final HashMap<String, ArrayList<Account>> accounts;
    private int accountCount;

    public Data(){
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
        load();
    }

    private void load(){
        try{
            Scanner sc = new Scanner(new File("users.txt"));        //loads users
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(",");
                users.put(parts[0], parts[1]);
            }
            sc.close();

            sc = new Scanner(new File("accounts.txt"));     //loads accounts
            while(sc.hasNextLine()){
                accountCount++;
                String line = sc.nextLine();
                String[] parts = line.split(",");

                String id = parts[0];
                String owner = parts[1];
                double amount = Double.parseDouble(parts[2]);

                Account acc = new Account(id, amount);
                addAccount(acc, owner);
            }
            sc.close();
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

    public void writeUser(String user, String password){
        try{
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write(user + "," + password + "\n");
            writer.close();
            users.put(user, password);
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

    public void writeAccount(String user){
        try{
            FileWriter writer = new FileWriter("accounts.txt", true);
            accountCount++;
            int newId = accountCount;
            String idFull = Settings.bankId + "/" + newId;
            writer.write(idFull + "," + user +"," + 0 + "\n");
            addAccount( new Account( idFull, 0), user );
            writer.close();
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

    public HashMap<String, String> getUsers() {
        return users;
    }
    public ArrayList<Account> getAccounts(String username){
        if(accounts.containsKey(username)){
            return accounts.get(username);
        }
        System.out.println("No accounts exist for this user");
        return new ArrayList<>();
    }
    public void addAccount(Account acc, String owner){
        ArrayList<Account> insideAcc;
        if(accounts.containsKey(owner)){
            insideAcc = accounts.get(owner);
        }else{
            insideAcc = new ArrayList<>();
        }
        insideAcc.add(acc);
        accounts.put(owner, insideAcc);
    }
}
