package org.example.Logic.PathFinding;

import org.example.Node;
import org.example.Path;

import java.util.ArrayList;

public interface PathFinder {
    void findPath(Node start, Node end);
    ArrayList<Path> getFinalPath();
    int getSteps();
}
