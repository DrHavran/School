package org.example.Logic;

import org.example.Data.Data;
import org.example.Logic.PathFinding.AStar;
import org.example.Logic.PathFinding.BFS;
import org.example.Logic.PathFinding.DFS;
import org.example.Logic.PathFinding.PathFinder;
import org.example.Node;
import org.example.Path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Logic {
    private final Data data;
    private final NodeModel model;
    private PathFinder pathFinder;

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
    public ArrayList<Path> findPath(long start, long end, String method) {
        if(method.equals("DFS")){
            this.pathFinder = new DFS();
        }else if (method.equals("BFS")) {
            this.pathFinder = new BFS();
        }else if (method.equals("A*")) {
            this.pathFinder = new AStar();
        }else{
            System.out.println("Invalid method");
            return null;
        }
        pathFinder.findPath(getNode(start), getNode(end));
        return pathFinder.getFinalPath();
    }
}