package org.server.radekniggatest.Objects;

import java.util.ArrayList;

public class Order {
    private int id;
    private String status;
    private ArrayList<OrderItem> items;
    private double totalPrice;

    public Order(int id, String status) {
        this.id = id;
        this.status = status;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
