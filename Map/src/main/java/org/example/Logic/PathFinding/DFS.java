package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;
import java.util.HashSet;

public class DFS implements PathFinder{

    private Node end;
    private final ArrayList<Path> finalPath;
    private int steps;

    private final HashSet<Node> visited;

    public DFS() {
        this.visited = new HashSet<>();
        this.finalPath = new ArrayList<>();
    }

    public void findPath(Node start, Node end){
        this.end = end;
        visited.clear();
        finalPath.clear();
        steps = 0;

        nextStep(start);
        System.out.println("DFS took " + steps + " steps");
    }

    private boolean nextStep(Node current){
        steps++;
        visited.add(current);
        for(Node node : current.getPaths()){
            if(node == end){
                System.out.println("found end");
                return true;
            }
            if (!visited.contains(node)) {
                if (nextStep(node)) {
                    finalPath.add(new Path(current, node));
                    return true;
                }
            }
        }
        return false;
    }

    public int getSteps() {
        return steps;
    }

    public ArrayList<Path> getFinalPath() {
        return finalPath;
    }
}
