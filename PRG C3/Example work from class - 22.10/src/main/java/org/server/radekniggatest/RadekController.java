package org.server.radekniggatest;

import org.server.radekniggatest.Modules.ChangeStatusFormModel;
import org.server.radekniggatest.Modules.PlaceOrderFormModel;
import org.server.radekniggatest.Modules.PlaceOrderItemFormModel;
import org.server.radekniggatest.Objects.Item;
import org.server.radekniggatest.Objects.Order;
import org.server.radekniggatest.Objects.OrderItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RadekController {
    private final HashMap<Integer, Order> orders;

    //Existujicí produkty
    private final HashMap<Integer, Item> products;

    public RadekController() {
        this.orders = new HashMap<>();
        this.products = new HashMap<>();

        products.put(1, new Item(1, "Jirka Sirka", 67.67));
        products.put(2, new Item(2, "Mandarinka", 7));
        products.put(3, new Item(3, "Hadomír Radáček", -100));
    }

    @GetMapping("/products")
    public ArrayList<Item> getProducts(){
        return new ArrayList<>(products.values());
    }

    @PostMapping("/orders")
    public Order placeOrder(
            @RequestBody PlaceOrderFormModel orderForm
    ){
        ArrayList<Integer> alreadySeen = new ArrayList<>();
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        // Checks of the order
        if(orderForm.getItems().size() <= 0){
            System.out.println("Musíš mít aspoň 1 item noobe");
            return null;
        }

        for(PlaceOrderItemFormModel item : orderForm.getItems()){
            if(!products.containsKey(item.getProductId())){
                System.out.println("Item id není valid :<");
                return null;
            }
            if(item.getQuantity() <= 0){
                System.out.println("Quantity musí být kladná");
                return null;
            }

            if(alreadySeen.contains(item.getProductId())){
                System.out.println("Already seen this productID");
                return null;
            }
            alreadySeen.add(item.getProductId());

            //Create the item itself
            Item product = products.get(item.getProductId()); // new Item(1, "Jirka Sirka", 67.67)

            orderItems.add(new OrderItem(
                    item.getProductId(),
                    product.getName(),
                    item.getQuantity(),
                    product.getPrice(),
                    product.getPrice() * item.getQuantity()
            ));
        }

        Order order = new Order(orders.size() + 1, "CREATED");
        order.setItems(orderItems);
        orders.put(order.getId(), order);

        // Sums the total of the order
        double total = order.getItems().stream().mapToDouble(OrderItem::getSubTotal).sum();
        order.setTotalPrice(total);

        return order;
    }


    @GetMapping("/orders")
    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(
        @PathVariable int id
    ){
        if(!orders.containsKey(id)){
            System.out.println("Order of that id isnt placed :3");
            return null;
        }
        return orders.get(id);
    }

    @PostMapping("/orders/{id}/status")
    public Order changeStatus(
            @PathVariable int id,
            @RequestBody ChangeStatusFormModel status
    ){
        Order order = orders.get(id);
        order.setStatus(status.getStatus());
        return order;
    }
}