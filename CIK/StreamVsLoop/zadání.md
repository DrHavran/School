# OrderService — Method Descriptions

Practice choosing the right tool for each method.

> **Rules:**
> - `.forEach()` is banned.
> - Prefer clarity over cleverness.

---

### 1. `sumPaidOrdersForCustomer`
Return the sum of `total` for all orders that:
- belong to the given `customerId`, **and**
- have status `PAID`.

If the customer has no paid orders, return `0.0`.

---

### 2. `findFirstRepeatedCustomerOrderId`
Return the `orderId` of the **first order** whose `customerId` has already appeared earlier in the list.

If no customer repeats, return `null`.

**Example:**
```
orders: [A1 (cust=alice), B2 (cust=bob), C3 (cust=alice)]
→ returns "C3"   // alice already appeared on A1
```

---

### 3. `groupOrdersByCustomer`
Group orders by their `customerId`, but only include orders whose status is in `allowedStatuses`.

Return a `HashMap` where:
- the **key** is a `customerId`,
- the **value** is an `ArrayList<Order>` of that customer's orders with an allowed status.

If no orders match, return an empty map.

---

### 4. `findCustomerWhoCrossedLimit`
Walk the orders in list order, adding `total` to a running sum. As soon as the running sum **strictly exceeds** `limit`, return the `customerName` of the order that caused it to cross.

If the running sum never exceeds `limit`, return `null`.

**Example:**
```
orders: [30, 40, 50], limit = 100
running: 30 → 70 → 120   // crosses on the third order
→ returns that order's customerName
```

---

### 5. `findTransactionsForCustomer`
Return all transactions belonging to the given `customerId`.

If none match, return an empty `ArrayList`.

---

### 6. `applyDiscountToActiveOrders`
Modify each order **in place** (do not create new order objects). For every order whose status is **not** `CANCELLED`:
- multiply its `total` by `(1 - discount)`,
- print the modified `orderId` (one line per order).

Canceled orders are skipped — their totals stay unchanged and nothing is printed for them.

**Example (discount = 0.10):**
```
Discounted: ORD-001
Discounted: ORD-003
```