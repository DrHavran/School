package org.server.server.Parts;

public class Account {
    private final String owner;
    private final String id;
    private double amount;

    public Account(String owner, String id, double amount) {
        this.owner = owner;
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }
    public double getAmount(){ return amount;}
    public void addAmount(double amount) {
        this.amount += amount;
    }
    public String getOwner() {
        return owner;
    }
}