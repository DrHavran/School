package Printer;

public class LaserPrinter implements Printer{

    public LaserPrinter() {

    }

    @Override
    public void print(String message){
        System.out.println("Laser printer: " + message);
    }

    @Override
    public int getCostPerPage(){
        return (int)(Math.random() * 101);
    }
}
