package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;
import java.util.HashMap;

public class PathFinder {
    protected final ArrayList<Path> finalPath;
    protected int steps;

    protected final ArrayList<Node> visited;
    protected final HashMap<Node, Double> nodes;
    protected final ArrayList<Node> queue;

    public PathFinder() {
        this.finalPath = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.nodes = new HashMap<>();
        this.steps = 0;
    }

    public void findPath(Node start, Node end) {}

    protected void clean(){
        finalPath.clear();
        visited.clear();
        queue.clear();
        nodes.clear();
        steps = 0;
    }

    protected Node findSmallest(ArrayList<Node> list){
        Node minNode = list.getFirst();
        double min = Double.POSITIVE_INFINITY;
        for(Node next : list){
            if(!nodes.containsKey(next)){
                continue;
            }
            if(nodes.get(next) < min){
                min = nodes.get(next);
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
