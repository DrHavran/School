import java.util.ArrayList;

public class Player {
    private String name;
    private final ArrayList<Item> items;
    private Room currentRoom;

    public Player(String name, Room curentRoom) {
        this.name = name;
        this.currentRoom = curentRoom;
        this.items = new ArrayList<>();
    }

    public void takeItem(String string){
        Item item = currentRoom.checkForItem(string);
        if(item != null){
            items.add(item);
            currentRoom.removeItem(item);
        }
    }

    public void goTo(String string){
        Room room = currentRoom.checkConnection(string);
        if(room != null){
            currentRoom = room;
        }
    }

    public void printItems(){
        if(items.isEmpty()){
            System.out.println("You have no items");
            return;
        }
        for(Item item : items){
            System.out.println(item.getName());
        }
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
