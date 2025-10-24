import java.util.ArrayList;

public class Room {
    private String name;
    private final ArrayList<Item> items;
    private final ArrayList<Room> rooms;

    public Room(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public Item checkForItem(String name){
        if(items.isEmpty()){
            System.out.println("No items in room");
            return null;
        }
        for(Item item : items){
            if(item.getName().equals(name)){
                return item;
            }
        }
        System.out.println("Item not found");
        return null;
    }

    public void printConnections(){
        if(rooms.isEmpty()){
            System.out.println("You are stuck lule");
            return;
        }
        for(Room room : rooms){
            System.out.println(room.getName());
        }
    }

    public void printItems(){
        if(items.isEmpty()){
            System.out.println("No items in the room");
            return;
        }
        for(Item item : items){
            System.out.println(item.getName());
        }
    }

    public Room checkConnection(String string){
        if(rooms.isEmpty()){
            System.out.println("No connections lule");
            return null;
        }
        for(Room room : rooms){
            if(room.getName().equals(string)){
                return room;
            }
        }
        System.out.println("No room found");
        return null;
    }

    public String getName() {
        return name;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void addItem(String name){
        items.add(new Item(name));
    }

    public void addRoom(Room room){
        rooms.add(room);
    }
}
