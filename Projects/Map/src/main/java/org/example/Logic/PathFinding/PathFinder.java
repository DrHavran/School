package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;

public class PathFinder {
    protected final ArrayList<Path> finalPath;
    protected int steps;

    protected final ArrayList<Node> visited;
    protected final ArrayList<Node> queue;

    public PathFinder() {
        this.finalPath = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.steps = 0;
    }

    public void findPath(Node start, Node end) {}

    protected Node findSmallest(ArrayList<Node> list){
        Node minNode = list.getFirst();
        double min = minNode.getValue();
        for(Node next : list){
            if(next.getValue() < min){
                min = next.getValue();
                minNode = next;
            }
        }
        return minNode;
    }

    protected double calculateDistance(Node start, Node end){
        double lon = start.getLongitude() - end.getLongitude();
        double lan = start.getLatitude() - end.getLatitude();
        return Math.sqrt(Math.pow(lon, 2) + Math.pow(lan, 2));
    }

    public double getLength(){
        double total = 0;
        for(Path p : finalPath){
            total += p.getLength();
        }
        return total;
    }

    public int getSteps(){
        return this.steps;
    }
    public ArrayList<Path> getFinalPath(){
        return finalPath;
    }
}
