package Printer;

public class VirtualPDFPrinter implements Printer {

    public VirtualPDFPrinter() {

    }

    @Override
    public void print(String message){
        System.out.println("Virtual PDF Printer: " + message);
    }

    @Override
    public int getCostPerPage(){
        return 0;
    }
}
