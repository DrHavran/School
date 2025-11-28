package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

public class BFS extends PathFinder {

    public BFS() {
        super();
    }

    public void findPath(Node start, Node end) {
        clean();

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
                        createNormalPath(node);
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

    private void createNormalPath(Node node) {

        Node selected = node;

        while(selected.getParent() != null){
            finalPath.add(new Path(selected, selected.getParent()));
            selected = selected.getParent();
        }

    }
}
