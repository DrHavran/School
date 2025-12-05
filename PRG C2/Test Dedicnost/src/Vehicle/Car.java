package Vehicle;

public class Car extends Vehicle {

    public Car() {
        super();
        maxFuel = 100;
        fuelType = "Diesel";
    }

    @Override
    public void move(int distance){
        if(fuel - distance >= 0){
            System.out.println("Traveled " + distance + " miles");
            fuel -= distance;
            System.out.println("You currently have " + fuel + " fuel left");
        }else{
            System.out.println("You dont have enough fuel");
        }
    }

    @Override
    public void refuel(int count){
        if(fuel + count > maxFuel){
            fuel = maxFuel;
        }else {
            fuel += count;
        }
        System.out.println("Refilled to " + fuel);
    }
}
