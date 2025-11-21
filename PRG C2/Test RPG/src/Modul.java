import java.util.ArrayList;

public class Modul {
    private final String name;
    private final ArrayList<Item> items;
    private final ArrayList<Modul> moduls;

    public Modul(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.moduls = new ArrayList<>();
    }

    public void connectModul(Modul modul){
        moduls.add(modul);
        modul.addModul(this);
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

    public Modul checkConnection(String string){
        if(moduls.isEmpty()){
            System.out.println("No connections lule");
            return null;
        }
        for(Modul room : moduls){
            if(room.getName().equals(string)){
                return room;
            }
        }
        System.out.println("No room found");
        return null;
    }

    public void printConnections(){
        if(moduls.isEmpty()){
            System.out.println("You are stuck lule");
            return;
        }
        for(Modul room : moduls){
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

    public String getName() {
        return name;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void addItem(String name){
        items.add(new Item(name));
    }

    public void addModul(Modul room){
        moduls.add(room);
    }
}
