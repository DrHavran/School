import java.util.ArrayList;
import java.util.HashMap;

public class Logic {
    private Node root;
    private final Data data;

    public Logic() {
        this.data = new Data();
        generateATree();
    }

    private void generateATree() {
        root = new Node();
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(root);

        for(HashMap<String, Object> animal : data.getAnimals()){
            System.out.println(animal.get(("Name")));
            System.out.println(root.check(animal));
        }

        while(!queue.isEmpty()) {
            Node current = queue.removeFirst();
        }
    }
}
