import java.util.ArrayList;

public class Player {
    private final ArrayList<Item> items;
    private Room currentRoom;

    public Player(Room curentRoom) {
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
            System.out.println("no items");
            return;
        }
        for(Item item : items){
            System.out.print(item.getName() + ", ");
        }
        System.out.println();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
