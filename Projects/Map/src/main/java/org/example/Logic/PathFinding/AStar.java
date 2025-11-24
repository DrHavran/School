package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

public class AStar extends PathFinder {

    public AStar() {
        super();
    }

    @Override
    public void findPath(Node start, Node end){
        clean();

        nodes.put(start, 0.0);
        visited.add(start);
        queue.add(start);

        while(!queue.isEmpty()){
            Node selected = findSmallest(queue);
            queue.remove(selected);
            for(Node node : selected.getPaths()){
                if(!nodes.containsKey(node)){
                    nodes.put(node, Double.POSITIVE_INFINITY);
                }

                if(node == end){
                    //System.out.println("found end");
                    //System.out.println("A* took " + steps + " steps");
                    createPath(node);
                    return;
                }

                if(!visited.contains(node)){
                    steps++;
                    double fromStart = nodes.get(selected) + calculateDistance(selected, node);
                    double fromEnd = calculateDistance(node, end);

                    if(nodes.get(node) > fromStart + fromEnd){
                        nodes.replace(node, fromStart + fromEnd);
                        queue.add(node);
                    }
                }
            }
            visited.add(selected);
        }
        //System.out.println("Didnt find a path");
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
