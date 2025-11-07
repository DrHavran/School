package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;

public class BFS implements PathFinder {
    private final ArrayList<Path> finalPath;
    private int steps;

    private final ArrayList<Node> queue;
    private final ArrayList<Node> visited;

    public BFS() {
        this.finalPath = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.visited = new ArrayList<>();
    }

    public void findPath(Node start, Node end) {
        queue.clear();
        finalPath.clear();
        visited.clear();
        steps = 0;

        queue.add(start);
        visited.add(start);
        start.setParent(null);

        while (!queue.isEmpty()) {
            Node selected = queue.removeFirst();
            for(Node node : selected.getPaths()){
                steps++;
                if(!visited.contains(node)){
                    node.setParent(selected);
                    visited.add(node);
                    if(node.equals(end)){
                        createPath(node);
                        System.out.println("Found end");
                        System.out.println("BFS took " + steps + " steps");
                        return;
                    } else {
                        queue.add(node);
                    }
                }
            }
        }
        System.out.println("Didnt find a path");
    }

    private void createPath(Node node) {

        Node selected = node;

        while(selected.getParent() != null){
            finalPath.add(new Path(selected, selected.getParent()));
            selected = selected.getParent();
        }

    }

    public ArrayList<Path> getFinalPath(){ return finalPath; }
    public int getSteps(){ return steps; }
}
