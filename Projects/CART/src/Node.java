import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private Node leftBranch, rightBranch;
    private ArrayList<HashMap<String, String>> points;
    private String checkString;
    private Check checkReq;

    public Node() {
        checkString = "";
        this.points = new ArrayList<>();
    }

    public boolean check(HashMap<String, String> animal){
        return checkReq.check(Double.parseDouble(animal.get(checkString)));
    }

    public void setCheckReq(Check req) {
        checkReq = req;
    }
    public void setPoints(ArrayList<HashMap<String, String>> points) {
        this.points = points;
    }
    public void addPoint(HashMap<String, String> animal) {
        points.add(animal);
    }
    public void setCheckString(String checkString) {
        this.checkString = checkString;
    }
    public void setLeftBranch(Node leftBranch) {
        this.leftBranch = leftBranch;
    }
    public void setRightBranch(Node rightBranch) {
        this.rightBranch = rightBranch;
    }

    public Node getLeftBranch() {
        return leftBranch;
    }
    public Node getRightBranch() {
        return rightBranch;
    }
    public String getCheckString() {
        return checkString;
    }
    public ArrayList<HashMap<String, String>> getPoints() {
        return points;
    }
}