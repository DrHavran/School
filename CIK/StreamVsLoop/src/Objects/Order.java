package Objects;

public class Order {
    private final String customerId;
    private final String customerName;
    private final String orderId;
    private double total;
    private final OrderStatus status;

    public Order(String customerId, String customerName, String orderId, double total, OrderStatus status) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderId = orderId;
        this.total = total;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getOrderId() {
        return orderId;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public OrderStatus getStatus() {
        return status;
    }
}
