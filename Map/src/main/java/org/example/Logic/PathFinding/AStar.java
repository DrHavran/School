package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;

public class AStar implements PathFinder {
    private final ArrayList<Path> finalPath;
    private int steps;

    private final ArrayList<Node> visited;
    private final ArrayList<Node> queue;

    public AStar() {
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
            Node selected = findSmallest(queue);
            queue.remove(selected);
            for(Node node : selected.getPaths()){
                if(node == end){
                    System.out.println("found end");
                    System.out.println("A* took " + steps + " steps");
                    createPath(node);
                    return;
                }
                if(!visited.contains(node)){
                    steps++;
                    double fromStart = selected.getValue() + calculateDistance(selected, node);
                    double fromEnd = calculateDistance(node, end);

                    if(node.getValue() > fromStart + fromEnd){
                        node.setValue(fromStart + fromEnd);
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
            Node next = findSmallest(selected.getPaths());
            finalPath.add(new Path(selected, next));
            selected = next;
        }
    }

    private double calculateDistance(Node start, Node end){
        double lon = start.getLongitude() - end.getLongitude();
        double lan = start.getLatitude() - end.getLatitude();
        return Math.sqrt(Math.pow(lon, 2) + Math.pow(lan, 2));
    }

    private Node findSmallest(ArrayList<Node> list){
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

    public ArrayList<Path> getFinalPath(){
        return finalPath;
    }
    public int getSteps(){
        return steps;
    }
}
