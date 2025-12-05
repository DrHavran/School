package Vehicle;

public class Bike extends Vehicle {
    public Bike() {
        super();
        fuelType = "Human";
    }

    @Override
    public void move(int distance){
        System.out.println("Traveled " + distance + " miles");
    }
}
