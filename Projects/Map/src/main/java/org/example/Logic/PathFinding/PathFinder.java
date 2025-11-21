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

    public int getSteps(){
        return this.steps;
    }
    

}
