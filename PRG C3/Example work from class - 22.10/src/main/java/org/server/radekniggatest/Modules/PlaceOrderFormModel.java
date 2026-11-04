package org.server.radekniggatest.Modules;

import java.util.ArrayList;

public class PlaceOrderFormModel {
    private ArrayList<PlaceOrderItemFormModel> items;

    public ArrayList<PlaceOrderItemFormModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<PlaceOrderItemFormModel> items) {
        this.items = items;
    }
}
