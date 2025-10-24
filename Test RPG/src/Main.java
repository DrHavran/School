import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Room kitchen = new Room("kitchen");
        Room livingRoom = new Room("living");
        Room bathroom = new Room("bathroom");

        Player player = new Player( livingRoom);
        livingRoom.addItem("potato");

        connectRooms(kitchen, livingRoom);
        connectRooms(bathroom, livingRoom);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            Room currentRoom = player.getCurrentRoom();

            System.out.println("You are currently in: " + currentRoom.getName());
            System.out.println();

            System.out.println("You can go to: ");
            currentRoom.printConnections();
            System.out.println();

            System.out.println("On the ground there is: ");
            currentRoom.printItems();
            System.out.println();

            System.out.println("You currently have: ");
            player.printItems();
            System.out.println();

            System.out.println("If you need help write: HELP");
            System.out.println();
            System.out.println("Insert command: ");

            String line = sc.nextLine();
            System.out.println();
            if(line.equals("exit")) {
                exit = true;
            }else if (line.contains("take ")) {
                player.takeItem(line.split(" ")[1]);
            }else if (line.contains("go to ")){
                player.goTo(line.split(" ")[2]);
            }else if (line.equals("HELP")) {
                System.out.println("To go to a different room: go to <room>");
                System.out.println("To pick up an item: take <item>");
                System.out.println("To exit the app: exit");
            }else{
                System.out.println("Unknown command, try again");
                System.out.println();
            }
        }
    }
    public static void connectRooms(Room one, Room two) {
        one.addRoom(two);
        two.addRoom(one);
    }
}