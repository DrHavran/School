package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

public class DSA extends PathFinder {

    public DSA() {
        super();
    }

    @Override
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
            Node next = findSmallest(selected.getPaths());
            finalPath.add(new Path(selected, next));
            selected = next;
        }
    }
}
