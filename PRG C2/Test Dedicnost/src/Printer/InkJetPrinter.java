package Printer;

public class InkJetPrinter implements Printer {

    public InkJetPrinter() {

    }

    @Override
    public void print(String message){
        System.out.println("Int Jet Printer: " + message);
    }

    @Override
    public int getCostPerPage(){
        return (int)(Math.random() * 101);
    }
}
