package org.example;

import java.util.ArrayList;

public class Node {
    private final double longitude; //X
    private final double latitude;  //Y
    private final ArrayList<Node> paths;

    public Node(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.paths = new ArrayList<>();
    }

    public void addNode(Node node) {
        paths.add(node);
    }

    public double getScore(Node end){
        return Math.sqrt(Math.pow(longitude-end.getLongitude(), 2) + Math.pow(latitude-end.getLatitude(), 2));
    }

    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public ArrayList<Node> getPaths() { return paths; }
}