import Objects.Order;
import Objects.OrderStatus;
import Objects.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Data data = new Data();
        OrderService service = new OrderServiceImpl();

        ArrayList<Order> orders = data.getOrders();
        ArrayList<Transaction> transactions = data.getTransactions();

        System.out.println("========== 1. sumPaidOrdersForCustomer ==========");
        System.out.println("Customer C-1001 (Alice, PAID only)");
        System.out.println("  Expected: 120.0");
        System.out.println("  Actual  : " + service.sumPaidOrdersForCustomer("C-1001", orders));

        System.out.println("Customer C-1003 (Charlie, PAID only)");
        System.out.println("  Expected: 200.0");
        System.out.println("  Actual  : " + service.sumPaidOrdersForCustomer("C-1003", orders));

        System.out.println("Customer C-9999 (no orders)");
        System.out.println("  Expected: 0.0");
        System.out.println("  Actual  : " + service.sumPaidOrdersForCustomer("C-9999", orders));

        System.out.println();
        System.out.println("========== 2. findFirstRepeatedCustomerOrderId ==========");
        System.out.println("Orders: ORD-001(C-1001), ORD-002(C-1002), ORD-003(C-1003),");
        System.out.println("        ORD-004(C-1001) <- repeat, ...");
        System.out.println("  Expected: ORD-004");
        System.out.println("  Actual  : " + service.findFirstRepeatedCustomerOrderId(orders));

        System.out.println();
        System.out.println("========== 3. groupOrdersByCustomer ==========");
        ArrayList<OrderStatus> allowed = new ArrayList<>();
        allowed.add(OrderStatus.PAID);
        allowed.add(OrderStatus.PENDING);

        HashMap<String, ArrayList<Order>> grouped =
                service.groupOrdersByCustomer(orders, allowed);

        System.out.println("Allowed statuses: PAID, PENDING");
        System.out.println("  Expected keys and sizes:");
        System.out.println("    C-1001 -> 2 orders (ORD-001, ORD-008)   [ORD-004 CANCELLED excluded]");
        System.out.println("    C-1002 -> 1 order  (ORD-002)            [ORD-006 COMPLETED excluded]");
        System.out.println("    C-1003 -> 2 orders (ORD-003, ORD-007)");
        System.out.println("    C-1004 -> 1 order  (ORD-005)");
        System.out.println("  Actual:");
        for (String customerId : grouped.keySet()) {
            ArrayList<Order> list = grouped.get(customerId);
            System.out.print("    " + customerId + " -> " + list.size() + " orders (");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).getOrderId());
                if (i < list.size() - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        System.out.println();
        System.out.println("========== 4. findCustomerWhoCrossedLimit ==========");
        System.out.println("limit = 400");
        System.out.println("  running: 120 -> 200 -> 400 -> 445 (ORD-004, Alice)");
        System.out.println("  Expected: Alice");
        System.out.println("  Actual  : " + service.findCustomerWhoCrossedLimit(orders, 400));

        System.out.println("limit = 300");
        System.out.println("  running: 120 -> 200 -> 400 (ORD-003, Charlie)");
        System.out.println("  Expected: Charlie");
        System.out.println("  Actual  : " + service.findCustomerWhoCrossedLimit(orders, 300));

        System.out.println("limit = 10000");
        System.out.println("  Expected: null");
        System.out.println("  Actual  : " + service.findCustomerWhoCrossedLimit(orders, 10000));

        System.out.println();
        System.out.println("========== 5. findTransactionsForCustomer ==========");
        System.out.println("Customer C-1001 (Alice) — orders ORD-001, ORD-004, ORD-008");
        System.out.println("  Expected: 4 transactions (ORD-001 paid, ORD-001 refund, ORD-004, ORD-008)");
        ArrayList<Transaction> aliceTx =
                service.findTransactionsForCustomer("C-1001", transactions);
        System.out.println("  Actual  : " + aliceTx.size() + " transactions");
        for (Transaction t : aliceTx) {
            System.out.println("    " + t.getOrderId() + "  amount=" + t.getAmount() + "  paid=" + t.isPaid());
        }

        System.out.println();
        System.out.println("========== 6. applyDiscountToActiveOrders ==========");
        System.out.println("discount = 0.10 (10% off non-cancelled orders)");
        System.out.println("  Expected prints: ORD-001, ORD-002, ORD-003, ORD-005, ORD-006, ORD-007, ORD-008");
        System.out.println("  (ORD-004 is CANCELLED and must be skipped)");
        System.out.println("  Actual:");
        service.applyDiscountToActiveOrders(0.10, orders);

        System.out.println();
        System.out.println("  After discount, ORD-001 total expected: 120.0 * 0.9 = 108.0");
        System.out.println("  Actual ORD-001 total: " + orders.getFirst().getTotal());
    }
}