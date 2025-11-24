package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

public class DSA extends PathFinder {

    public DSA() {
        super();
    }

    @Override
    public void findPath(Node start, Node end){
        clean();

        nodes.put(start, 0.0);
        queue.add(start);

        while(!queue.isEmpty()){
            Node selected = findSmallest(queue);
            queue.remove(selected);
            for(Node node : selected.getPaths()){
                if(!nodes.containsKey(node)){
                    nodes.put(node, Double.POSITIVE_INFINITY);
                }

                if(node == end){
                    System.out.println("found end");
                    System.out.println("DSA took " + steps + " steps");
                    createPath(node);
                    return;
                }
                if(!visited.contains(node)){
                    steps++;
                    if(nodes.get(selected) + calculateDistance(selected, node) < nodes.get(node)){
                        nodes.replace(node, nodes.get(selected) + calculateDistance(selected, node));
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

        while(nodes.get(selected) != 0){
            Node next = findSmallest(selected.getPaths());
            finalPath.add(new Path(selected, next));
            selected = next;
        }
    }
}
