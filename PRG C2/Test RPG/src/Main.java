import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Modul reactor = new Modul("reactor");
        Modul storage = new Modul("storage");
        Modul dock = new Modul("dock");

        Player player = new Player(storage);
        storage.addItem("key");

        connectRooms(reactor, storage);
        connectRooms(dock, storage);

        Scanner sc = new Scanner(System.in);
        String line;

        do{
            Modul currentModul = player.getCurrentModul();

            System.out.println("You are currently in: " + currentModul.getName());
            System.out.println();

            System.out.println("You can go to: ");
            currentModul.printConnections();
            System.out.println();

            System.out.println("On the ground there is: ");
            currentModul.printItems();
            System.out.println();

            System.out.println("You currently have: ");
            player.printItems();
            System.out.println();

            System.out.println("If you need help write: HELP");
            System.out.println();
            System.out.println("Insert command: ");

            line = sc.nextLine();
            System.out.println();
            if (line.contains("take ")) {
                player.takeItem(line.split(" ")[1]);
            }else if (line.contains("go to ")){
                player.goTo(line.split(" ")[2]);
            }else if (line.equals("HELP")) {
                System.out.println("To go to a different modul: go to <room>");
                System.out.println("To pick up an item: take <item>");
                System.out.println("To exit the app: exit");
            }else{
                if(!line.equals("exit")){
                    System.out.println("Unknown command, try again");
                    System.out.println();
                }
            }
        }while(!line.equals("exit"));
    }
    public static void connectRooms(Modul one, Modul two) {
        one.addModul(two);
        two.addModul(one);
    }
}