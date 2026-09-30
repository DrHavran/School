import Objects.Order;
import Objects.OrderStatus;
import Objects.Transaction;

import java.util.ArrayList;

public class Data {
    private final ArrayList<Order> orders;
    private final ArrayList<Transaction> transactions;

    public Data() {
        this.orders = new ArrayList<>();
        this.transactions = new ArrayList<>();
        fillInData();
    }

    private void fillInData() {
        // Orders: (customerId, customerName, orderId, total, status)
        orders.add(new Order("C-1001", "Alice",   "ORD-001", 120.0, OrderStatus.PAID));
        orders.add(new Order("C-1002", "Bob",     "ORD-002",  80.0, OrderStatus.PENDING));
        orders.add(new Order("C-1003", "Charlie", "ORD-003", 200.0, OrderStatus.PAID));
        orders.add(new Order("C-1001", "Alice",   "ORD-004",  45.0, OrderStatus.CANCELLED));
        orders.add(new Order("C-1004", "Dave",    "ORD-005", 150.0, OrderStatus.PAID));
        orders.add(new Order("C-1002", "Bob",     "ORD-006",  60.0, OrderStatus.COMPLETED));
        orders.add(new Order("C-1003", "Charlie", "ORD-007",  90.0, OrderStatus.PENDING));
        orders.add(new Order("C-1001", "Alice",   "ORD-008",  30.0, OrderStatus.PENDING));

        // Transactions: (orderId, amount, paid)
        transactions.add(new Transaction("ORD-001", 120.0, true));
        transactions.add(new Transaction("ORD-002",  80.0, false));
        transactions.add(new Transaction("ORD-003", 200.0, true));
        transactions.add(new Transaction("ORD-004",  45.0, false));
        transactions.add(new Transaction("ORD-005", 150.0, true));
        transactions.add(new Transaction("ORD-006",  60.0, true));
        transactions.add(new Transaction("ORD-001", -10.0, false)); // refund for ORD-001
        transactions.add(new Transaction("ORD-008",  30.0, false));
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}