public class BankAcc {
    private double balance;
    private int accNumber;
    private Human owner;
    private Bank bank;

    public BankAcc(Human owner, Bank bank, double balance, int accNumber) {
        this.balance = balance;
        this.accNumber = accNumber;
        this.owner = owner;
        this.bank = bank;
    }

    public boolean isInDebt(){
        return balance < 0;
    }

    public double getBalance() {
        return balance;
    }

    public Human getOwner() {
        return owner;
    }
}
