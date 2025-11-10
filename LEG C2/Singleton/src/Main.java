public class Main {
    public static void main(String[] args) {
        Manager mg = Manager.getInstance();

        mg.addPerson(new Employee("Jára", 25, 150));

        for(int i = 0; i <30; i++){
            mg.MAKETHEMWORK();
        }
    }
}