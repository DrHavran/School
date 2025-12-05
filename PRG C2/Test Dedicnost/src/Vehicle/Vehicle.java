package Vehicle;

public abstract class Vehicle {
    protected int fuel;
    protected int maxFuel;
    protected String fuelType;

    public Vehicle() {
        this.fuel = 0;
    }

    public abstract void move(int distance);
    public void refuel(int count){}
}
