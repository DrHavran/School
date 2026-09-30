package Objects;

public class Transaction {
    private final String orderId;
    private final double amount;
    private final boolean paid;

    public Transaction(String orderId, double amount, boolean paid) {
        this.orderId = orderId;
        this.amount = amount;
        this.paid = paid;
    }

    public String getOrderId() {
        return orderId;
    }
    public double getAmount() {
        return amount;
    }
    public boolean isPaid() {
        return paid;
    }
}
