import java.util.ArrayList;

public class Player {
    private final ArrayList<Item> items;
    private Modul currentModul;

    public Player(Modul currentRoom) {
        this.currentModul = currentRoom;
        this.items = new ArrayList<>();
    }

    public void takeItem(String string){
        Item item = currentModul.checkForItem(string);
        if(item != null){
            items.add(item);
            currentModul.removeItem(item);
        }
    }

    public void goTo(String string){
        Modul room = currentModul.checkConnection(string);
        if(room != null){
            currentModul = room;
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

    public Modul getCurrentModul() {
        return currentModul;
    }
}
