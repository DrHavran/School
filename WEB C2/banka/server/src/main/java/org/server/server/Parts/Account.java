package org.server.server.Parts;

public class Account {
    private final String id;
    private final String type;
    private final double amount;

    public Account(String id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }
}
