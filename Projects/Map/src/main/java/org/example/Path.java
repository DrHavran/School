package org.example;

public class Path {
    private final Node start;
    private final Node end;

    public Path(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public double getLength(){
        double lon = start.getLongitude() - end.getLongitude();
        double lan = start.getLatitude() - end.getLatitude();
        return Math.sqrt(Math.pow(lon, 2) + Math.pow(lan, 2));
    }
    public Node getStart() {
        return start;
    }
    public Node getEnd() {
        return end;
    }
}
