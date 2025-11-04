package org.example.Logic.PathFinding;

import org.example.Node;

import java.util.HashSet;

public class DFS implements PathFinder{

    private Node end;
    private final HashSet<Node> visited;
    private int steps;

    public DFS() {
        this.visited = new HashSet<>();
        this.steps = 0;
    }

    public void findPath(Node start, Node end){
        this.end = end;
        nextStep(start, null);
        System.out.println(steps);
    };

    private boolean nextStep(Node current, Node parent){
        steps++;
        visited.add(current);
        for(Node node : current.getPaths()){
            if(node == end){
                System.out.println("found end");
                return true;
            }
            if (!visited.contains(node)) {
                if (nextStep(node, current)) {
                    return true;
                }
            }
        }
        System.out.println("No Path");
        return false;
    }

    public int getSteps() {
        return steps;
    }
}
