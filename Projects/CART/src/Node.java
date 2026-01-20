import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private Node leftBranch, rightNode;
    private ArrayList<HashMap<String, String>> animals;
    private String checkString;
    private Check checkReq;

    public Node() {
        checkString = "";
    }

    public boolean check(HashMap<String, String> animal){
        return checkReq.check(Double.parseDouble(animal.get(checkString)));
    }

    public void setCheckReq(Check req) {
        checkReq = req;
    }
    public void setAnimals(ArrayList<HashMap<String, String>> animals) {
        this.animals = animals;
    }
    public void addAnimal(HashMap<String, String> animal) {
        animals.add(animal);
    }
    public void setCheckString(String checkString) {
        this.checkString = checkString;
    }

    public ArrayList<HashMap<String, String>> getAnimals() {
        return animals;
    }
}