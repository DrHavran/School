import Vehicle.*;
import Printer.*;
import Units.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Car());
        vehicles.add(new Bike());
        vehicles.add(new Plane());

        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle.getClass().getSimpleName());
            vehicle.refuel(100);
            vehicle.move(10);
        }
        System.out.println();


        ArrayList<Printer> printers = new ArrayList<Printer>();
        printers.add(new LaserPrinter());
        printers.add(new InkJetPrinter());
        printers.add(new VirtualPDFPrinter());

        for(Printer printer : printers){
            printer.print("hehehehe");
            System.out.println(printer.getCostPerPage());
        }
        System.out.println();


        ArrayList<Unit> units = new ArrayList<>();
        units.add(new Mage("Kodytek"));
        units.add(new Archer("Sedláček"));
        units.add(new Warrior("Pešek"));

        for(Unit unit : units){
            ArrayList<Unit> targets = new ArrayList<>(units);
            targets.remove(unit);

            Unit target = targets.getFirst();
            unit.takeTurn(target);
        }
    }
}