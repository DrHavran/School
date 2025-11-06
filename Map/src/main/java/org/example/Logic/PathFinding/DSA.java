package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;

public class DSA implements PathFinder {
    private final ArrayList<Path> finalPath;
    private int steps;

    private final ArrayList<Node> visited;

    public DSA() {
        this.finalPath = new ArrayList<>();
        this.visited = new ArrayList<>();
    }

    public void findPath(Node start, Node end){

    }

    public ArrayList<Path> getFinalPath(){
        return finalPath;
    };
    public int getSteps(){
        return steps;
    };
}
