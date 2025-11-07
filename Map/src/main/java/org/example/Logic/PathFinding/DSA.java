package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;

public class DSA implements PathFinder {
    private final ArrayList<Path> finalPath;
    private int steps;

    private final ArrayList<Node> visited;
    private final ArrayList<Node> queue;

    public DSA() {
        this.finalPath = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.queue = new ArrayList<>();
    }

    public void findPath(Node start, Node end){
        queue.clear();
        finalPath.clear();
        visited.clear();
        steps = 0;

        start.setValue(0);
        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){
            Node selected = popSmallest();
            for(Node node : selected.getPaths()){
                if(node == end){
                    System.out.println("found end");
                    System.out.println("DSA took " + steps + " steps");
                    createPath(node);
                    return;
                }
                if(!visited.contains(node)){
                    steps++;
                    if(selected.getValue() + calculateDistance(selected, node) < node.getValue()){
                        node.setValue(selected.getValue() + calculateDistance(selected, node));
                        queue.add(node);
                    }
                }
            }
            visited.add(selected);
        }
        System.out.println("Didnt find a path");
    }

    private void createPath(Node node){
        Node selected = node;

        while(selected.getValue() != 0){
            Node next = findSmallest(selected);
            finalPath.add(new Path(selected, next));
            selected = next;
        }
    }

    private double calculateDistance(Node start, Node end){
        double lon = start.getLongitude() - end.getLongitude();
        double lan = start.getLatitude() - end.getLatitude();
        return Math.sqrt(Math.pow(lon, 2) + Math.pow(lan, 2));
    }

    private Node findSmallest(Node node){
        double min = node.getValue();
        Node minNode = node;
        for(Node next : node.getPaths()){
            if(next.getValue() < min){
                min = next.getValue();
                minNode = next;
            }
        }
        return minNode;
    }

    private Node popSmallest(){
        double min = queue.getFirst().getValue();
        Node minNode = queue.getFirst();
        for(Node node : queue){
            if(node.getValue() < min){
                min = node.getValue();
                minNode = node;
            }
        }
        queue.remove(minNode);
        return minNode;
    }

    public ArrayList<Path> getFinalPath(){
        return finalPath;
    }
    public int getSteps(){
        return steps;
    }
}
