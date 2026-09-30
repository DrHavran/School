import Objects.Order;
import Objects.OrderStatus;
import Objects.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderService {

    double sumPaidOrdersForCustomer(String customerId, ArrayList<Order> orders);

    String findFirstRepeatedCustomerOrderId(ArrayList<Order> orders);

    HashMap<String, ArrayList<Order>> groupOrdersByCustomer(ArrayList<Order> orders, ArrayList<OrderStatus> allowedStatuses);

    String findCustomerWhoCrossedLimit(ArrayList<Order> orders, double limit);

    ArrayList<Transaction> findTransactionsForCustomer(String customerId, ArrayList<Transaction> transactions);

    void applyDiscountToActiveOrders(double discount, ArrayList<Order> orders);
}