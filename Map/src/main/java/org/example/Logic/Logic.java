package org.example.Logic;

import org.example.Data.Data;
import org.example.Node;
import org.example.Path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Logic {
    Data data;
    NodeModel model;

    public Logic() {
        this.data = new Data();
        this.model = data.createModel();
    }

    public Hashtable<Long, Node> getNodes() {return data.getNodes();}
    public ArrayList<Path> getPaths() {
        return data.getPaths();
    }
    public Node getNode(long id) {
        return data.getNodes().get(id);
    }

    public double scaleX(double number){
        return model.scaleX(number);
    }
    public double scaleY(double number){
        return model.scaleY(number);
    }

    public ArrayList<Path> colorAllPaths(Node node) {
        HashSet<Node> visited = new HashSet<>();
        HashSet<Node> unVisited = new HashSet<>();
        ArrayList<Path> paths = new ArrayList<>();

        unVisited.add(node);
        while(!unVisited.isEmpty()) {
            Node next = unVisited.iterator().next();
            for (Node selected : next.getPaths()){
                if(!visited.contains(selected)){
                    unVisited.add(selected);
                    paths.add(new Path(selected, next));
                }
            }
            unVisited.remove(next);
            visited.add(next);
        }

        return paths;
    }
    public void findPath(Node start, Node end) {}
}