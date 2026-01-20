import java.util.HashMap;

public class Node {
    private Node leftBranch, rightNode;
    private String checkString;
    private final Check checkReq;

    public Node() {
        checkReq = (i) -> ((Integer) i < 25);
        checkString = "Weight";
    }

    public boolean check(HashMap<String, Object> animal){
        return checkReq.check(animal.get(checkString));
    }
}
