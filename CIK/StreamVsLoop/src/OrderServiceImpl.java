import Objects.Order;
import Objects.OrderStatus;
import Objects.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderServiceImpl implements OrderService {

    public double sumPaidOrdersForCustomer(String customerId, ArrayList<Order> orders) {
        return 0;
    }

    public String findFirstRepeatedCustomerOrderId(ArrayList<Order> orders) {
        return "";
    }

    public HashMap<String, ArrayList<Order>> groupOrdersByCustomer(ArrayList<Order> orders, ArrayList<OrderStatus> allowedStatuses) {
        return new HashMap<>();
    }

    public String findCustomerWhoCrossedLimit(ArrayList<Order> orders, double limit) {
        return null;
    }

    public ArrayList<Transaction> findTransactionsForCustomer(String customerId, ArrayList<Transaction> transactions) {
        return new ArrayList<>();
    }

    public void applyDiscountToActiveOrders(double discount, ArrayList<Order> orders) {

    }
}
