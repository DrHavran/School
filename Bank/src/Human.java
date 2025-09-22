import java.util.ArrayList;

public class Human {
    private String firstName;
    private String lastName;
    private int age;
    private ArrayList<BankAcc> accounts = new ArrayList<>();

    public void newAccount(Bank bank, double balance){
        BankAcc account = new BankAcc(this, bank, balance, bank.numberOfAccounts()+1);
        accounts.add(account);
        bank.newAccount(account);
    }

    public Human(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public BankAcc getAccount(int index){
        if(accounts.size()<index){
            System.out.println("Account of that index does not exist");
            return null;
        }
        return accounts.get(index-1);
    }
}

