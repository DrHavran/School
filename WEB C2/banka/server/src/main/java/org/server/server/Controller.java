package org.server.server;

import org.server.server.Parts.Account;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
    private final Logic logic;

    public Controller(){
        this.logic = new Logic();
    }

    @PostMapping("/login")
    public String login(@RequestBody HashMap<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String instance = body.get("instance");

        return logic.checkLogin(username, password, instance);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody HashMap<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        return logic.checkSignUp(username, password);
    }

    @PostMapping("/getAccounts")
    public ArrayList<Account> getAccounts(@RequestBody HashMap<String, String> body) {
        String username = body.get("username");
        String instance = body.get("instance");

        return logic.getAccounts(username, instance);
    }

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody HashMap<String, String> body) {
        String username = body.get("username");
        String instance = body.get("instance");
        logic.createAccount(username, instance);
    }

    @PostMapping("/sendMoney")
    public String sendMoney(@RequestBody HashMap<String, String> body) {
        String username = body.get("username");
        String instance = body.get("instance");
        double amount = Double.parseDouble(body.get("amount"));
        String receiverId = body.get("receiverAccId");
        String senderId = body.get("senderAccId");
        return logic.sendMoney(username, instance, amount, receiverId, senderId);
    }

    @PostMapping("/getName")
    public String getName(@RequestBody HashMap<String, String> body) {
        String instance = body.get("instance");
        return logic.getName(instance);
    }

    @PostMapping("/logout")
    public void logOut(@RequestBody HashMap<String, String> body) {
        String instance = body.get("instance");
        String username = body.get("username");
        logic.logOut(instance, username);
    }
}
