package example.Parts;

public class Node {
    private double x, y, label;

    public Node(double x, double y, double label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public void set(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
    public double getLabel() { return label; }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
