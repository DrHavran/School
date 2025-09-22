import java.util.ArrayList;

public class Bank {
    private String name;
    private int code;
    private ArrayList<BankAcc> accounts = new ArrayList<>();
    private ArrayList<Human> customers = new ArrayList<>();

    public int numberOfAccounts(){
        return accounts.size();
    };

    public void newAccount(BankAcc account){
        if(!customers.contains(account.getOwner())){
            customers.add(account.getOwner());
        }
        accounts.add(account);
    }

    public Bank(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public double getTotalBalance() {
        double total = 0;
        for(BankAcc acc : accounts){
            total += acc.getBalance();
        }
        return total;
    }
    public int getClientCount() {
        return customers.size();
    }
}
